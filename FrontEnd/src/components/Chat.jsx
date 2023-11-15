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

  useEffect(() => {
    // Gestion de la réception de la liste des utilisateurs
    reducer.socket.on('userList', (users) => {
      setUsers(users);

      // Sélectionner le premier utilisateur par défaut
      if (users.length > 0) {
        setSender(users[0].login);
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
    reducer.socket.emit('joinRoom', { sender, receiver });
  };

  const leaveChat = () => {
    const roomNameText = getRoomName(sender, receiver);
    reducer.socket.emit('leaveRoom', roomNameText);
  };

  const getRoomName = (user1, user2) => {
    const sortedNames = [user1, user2].sort();
    return `${sortedNames[0]}_${sortedNames[1]}`;
  };

  return (
    <div id="chat-box">
      <h2>Chat</h2>
      <div class="input-group">
        <p>Qui êtes-vous ?</p>
        <select value={sender} onChange={(e) => setSender(e.target.value)}>
          {users.map((user) => (
            <option key={user.login} value={user.login}>
              {user.login}
            </option>
          ))}
        </select>
      </div>
      <div class="input-group">
        <p>Avec qui souhaitez-vous chatter ?</p>
        <select value={receiver} onChange={(e) => setReceiver(e.target.value)}>
          {users.map((user) => (
            <option key={user.login} value={user.login}>
              {user.login}
            </option>
          ))}
        </select>
      </div>
      <br />
      <button onClick={joinChat}>Rejoindre le chat</button>
      <p id="roomNameText">Vous êtes dans la room {getRoomName(sender, receiver)}</p>
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
        <button onClick={handleSendMessage} id="send-button">
          Envoyer
        </button>
      </div>
    </div>

  );
};

export default Chat;
