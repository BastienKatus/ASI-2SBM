// ChatComponent.js
import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';

const Chat = () => {
  const reducer = useSelector(state => state.reducer)
  const [inputMessage, setInputMessage] = useState('');
  const [messages, setMessages] = useState([]);
  const [users, setUsers] = useState([]);
  const [sender, setSender] = useState('');
  const [receiver, setReceiver] = useState('');
  const [isConnected, setIsConnected] = useState('');

  useEffect(() => {

  setIsConnected(false);
    // Gestion de la réception de la liste des utilisateurs
    reducer.socket.on('userList', (users) => {
    users = users.filter(user => user.login !== reducer.user.login);
    setUsers(users);

      // Sélectionner le premier utilisateur par défaut
      if (users.length > 0) {
        setSender(reducer.user.login);
        setReceiver(users[0].login);
      }
    });

    // Gestion de la réception de messages (chatMessage)
    reducer.socket.on('chatMessage', (data) => {
      setMessages((prevMessages) => [...prevMessages, `${data.sender}: ${data.message}`]);
    });

    reducer.socket.emit('chatpage')

    // Nettoyer les listeners lors du démontage du composant
    return () => {
      reducer.socket.off('userList');
      reducer.socket.off('chatMessage');
    };
  }, [reducer.socket]);

  const handleInputChange = (event) => {
    setInputMessage(event.target.value);
  };

  const handleSendMessage = () => {
    if (inputMessage && sender && receiver) {
      reducer.socket.emit('chatMessage', { sender, receiver, message: inputMessage });
      setInputMessage('');
    }
  };

  const joinChat = () => {
    leaveChat(); // Assurez-vous de quitter la salle avant de rejoindre une nouvelle
    setIsConnected(true);
    reducer.socket.emit('joinRoom', { sender, receiver });
  };
  const changeReceiver =(receiver)=>{
        setReceiver(receiver);
        setIsConnected(false);
  }
  const leaveChat = () => {
    const roomNameText = getRoomName(sender, receiver);
    setIsConnected(false);
    reducer.socket.emit('leaveRoom', roomNameText);
  };

  const getRoomName = (user1, user2) => {
    if(isConnected){
        const sortedNames = [user1, user2].sort();
        return `Vous êtes dans la room : ${sortedNames[0]}_${sortedNames[1]}`;
    }
    else{
        return "Vous n'êtes pas connectés";
    }
  };

  return (
    <div id="chat-box">
      <h2>Chat</h2>
      <div class="input-group">
        <p>Vous êtes : </p>
        <p>{
        reducer.user.login
        }</p>
      </div>
      <div class="input-group">
        <p>Avec qui souhaitez-vous chatter ?</p>
        <select value={receiver} onChange={(e) => changeReceiver(e.target.value)}>
          {users.map((user) => (
            <option key={user.login} value={user.login}>
              {user.login}
            </option>
          ))}
        </select>
      </div>
      <br />
        {!isConnected ? <button onClick={joinChat}>Rejoindre le chat</button>: <button onClick={leaveChat}>Quitter le chat</button>}
      <p id="roomNameText">{getRoomName(sender, receiver)}</p>
      <div id="message-box">
        <ul id="message-list">
          {messages.map((message, index) => (
            <li key={index}>{message}</li>
          ))}
        </ul>
      </div>
      <div class="input-group">
        <input
          type="text"
          value={inputMessage}
          onChange={handleInputChange}
          placeholder="Entrez votre message"
        />
        <button onClick={handleSendMessage} id="send-button">Envoyer</button>



      </div>
    </div>

  );
};

export default Chat;
