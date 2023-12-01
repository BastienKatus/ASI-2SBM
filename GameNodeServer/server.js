const express = require('express');
const app = express();
const http = require('http').Server(app);
const io = require('socket.io')(http, {
  cors: {
    origin: 'http://localhost:3000',
    methods: ['GET', 'POST'],
  },
});
const axios = require('axios');
const cors = require('cors');

app.use(cors());

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});


function sendChat(roomName, userSender, userReceiver, message){
  const data = {
    roomName: roomName,
    idSender: userSender,
    idReceiver: userReceiver,
    message: message
  };
  console.log("HELLO DATA in sendChat---->>",data)
  const url = 'http://localhost:8086/chat';
  axios.post(url, data)
    .then((response) => {
      console.log("sendChat RESPONSE -->",response);
      console.log("sendChat RESPONSE -->",JSON.stringify(response.data));
    })
    .catch((error) => {
      console.error('Erreur lors de l\'exÃ©cution de la requÃªte POST vers l\'API Spring Boot:', error);
    });
}

users = []
waitingUsers = []
userCards = []

io.on('connection', (socket) => {
  console.log('Utilisateur connecté' + socket.id);

  // Récupération de la liste des utilisateurs via l'API Users (backend springboot)
  axios.get('http://localhost:8080/users')
    .then((response) => {
      users = response.data;
      socket.emit('userList', users); // Émettre au client
    })
    .catch((error) => {
      console.error('Erreur lors de la récupération des utilisateurs depuis l\'API Spring Boot:', error);
    });

  socket.on('broadcast', (message) => {
    io.emit('broadcast', message); // Broadcast
  });

  socket.on('chatpage', (message) => {
    axios.get('http://localhost:8080/users')
        .then((response) => {
          const users = response.data;
          socket.emit('userList', users); // Émettre au client
        })
        .catch((error) => {
          console.error('Erreur lors de la récupération des utilisateurs depuis l\'API Spring Boot:', error);
        });
  });

  function getRoomName(user1, user2) {
    const sortedNames = [user1, user2].sort();
    return `${sortedNames[0]}_${sortedNames[1]}`;
  }

  socket.on('joinRoom', (data) => {
    const roomName = getRoomName(data.sender, data.receiver);
    socket.join(roomName);
    console.log(`${socket.id} a rejoint la room ${roomName}`);
  });

  socket.on('leaveRoom', (roomName) => {
    socket.leave(roomName);
    console.log(`${socket.id} a quitté la room ${roomName}`);
  });

  socket.on('chatMessage', (data) => {
    const roomName = getRoomName(data.sender, data.receiver);
    io.to(roomName).emit('chatMessage', data);

    let sender = data.sender;
    let receiver = data.receiver;
    let message = data.message;
    console.log(sender,receiver);
    for (let i = 0; i < users.length; i++) {
      const user = users[i];
      if(user.login == data.sender){
        sender = user.id
      }
      if(user.login == data.receiver){
        receiver = user.id
      }
    }
    console.log(sender,receiver);
    // Appel Ã  SendChat to save message in DB
    sendChat(roomName, sender, receiver, message)
  });

  socket.on('disconnect', () => {
    console.log('Utilisateur déconnecté');
  });

  socket.on('joinGame', (user, card) => {
    console.log(user);
    console.log(card);
    waitingUsers.push(user);
    userCards.push(card);
    socket.join(waitingUsers[0].login);
    if(waitingUsers.length == userCards.length){
      if(waitingUsers.length > 1){
        const roomName = getRoomName(waitingUsers[0].login, waitingUsers[1].login);
        //socket.emit('gameInfo', roomName, waitingUsers, userCards);
        io.to(waitingUsers[0].login).emit('gameInfo', roomName, waitingUsers, userCards);
        pickWinner();
        waitingUsers.splice(0, waitingUsers.length);
        userCards.splice(0, userCards.length);
      }
      else{
        console.log(`Le user ${user.login} est en attente d'un autre joueur`)
      }
    }
    else{
      console.log("Erreur d'ajout des cartes et des utilisateurs à la room");
      waitingUsers.splice(0, waitingUsers.length);
      userCards.splice(0, userCards.length);
    }
  })
});

function pickWinner(){
  console.log(userCards);
  if(userCards[0].hp > userCards[1].hp){
    io.to(waitingUsers[0].login).emit('winner', waitingUsers[0]);
    console.log(`Le gagnant est ${waitingUsers[0].login}`);
  }
  else{
    io.to(waitingUsers[0].login).emit('winner', waitingUsers[1]);
    console.log(`Le gagnant est ${waitingUsers[1].login}`);
  }
}

http.listen(4000, () => {
  console.log('Serveur démarré sur le port 4000');
});

