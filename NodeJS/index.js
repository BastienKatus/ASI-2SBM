const socket = io();

    // Gestion de la réception de la liste des utilisateurs
  const userListDropdown = document.getElementById('user-list-dropdown');
  socket.on('userList', (users) => {
  // Afficher la liste des utilisateurs dans la liste déroulante
  users.forEach((user) => {
    const option = document.createElement('option');
    option.value = user.login; // Vous pouvez utiliser une autre propriété de l'utilisateur si nécessaire
    option.textContent = user.login;
    userListDropdown.appendChild(option);
  });

  // Vous pouvez également choisir de sélectionner le premier utilisateur par défaut
  if (users.length > 0) {
    userListDropdown.selectedIndex = 0;
  }
});

    // Gestion de l'envoi de messages
    const messageInput = document.getElementById('message-input');
    const sendButton = document.getElementById('send-button');
    sendButton.addEventListener('click', () => {
      const message = messageInput.value;
      if (message) {
        socket.emit('message', message);
        messageInput.value = '';
      }
    });

    // Gestion de la réception de messages
    socket.on('message', (message) => {
      const listItem = document.createElement('li');
      listItem.textContent = message;
      messageList.appendChild(listItem);
    });