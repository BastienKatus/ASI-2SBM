const express = require('express');
const app = express();
const http = require('http').Server(app);
const io = require('socket.io')(http);
const axios = require('axios'); // Module pour effectuer des requêtes HTTP

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html'); // Vous pouvez créer ce fichier HTML pour l'interface utilisateur.
});

io.on('connection', (socket) => {
  console.log('Utilisateur connecté');

  // Récupération de la liste des utilisateurs via l'API Spring Boot
  axios.get('http://localhost:8080/users')
    .then((response) => {
      const users = response.data; // Liste des utilisateurs
      socket.emit('userList', users); // Émettre la liste des utilisateurs au client
    })
    .catch((error) => {
      console.error('Erreur lors de la récupération des utilisateurs depuis l\'API Spring Boot:', error);
    });

  socket.on('message', (message) => {
    io.emit('message', message); // Diffusez le message à tous les utilisateurs connectés.
  });

  socket.on('disconnect', () => {
    console.log('Utilisateur déconnecté');
  });
});

http.listen(3000, () => {
  console.log('Serveur démarré sur le port 3000');
});
