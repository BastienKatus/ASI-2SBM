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

io.on('connection', (socket) => {
  console.log('Utilisateur connecté' + socket.id);

  // Récupération de la liste des utilisateurs via l'API Users (backend springboot)
  axios.get('http://localhost:8080/users')
    .then((response) => {
      const users = response.data;
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
  });

  socket.on('disconnect', () => {
    console.log('Utilisateur déconnecté');
  });
});

http.listen(4000, () => {
  console.log('Serveur démarré sur le port 4000');
});

