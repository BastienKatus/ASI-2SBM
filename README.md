# ASI-2SBM
Membres
- Maxime LEFEBVRE 
- Bastien KATUSZYNSKI 
- Simon MACHADO 
- Anthony SAINTOT

## Activités Réalisées
### Maxime :
Séance 1 :
Tableau comparatif des bus de communications

Séance 2 :
Diagramme de classe
Tableau comparatif des framework front
FRONT : Début de la réalisation du front-end

Séance 3 :
Participation diagramme d’architecture
FRONT : Navigation dans le front-end et pages Login, Register, Market (Acheter & Vendre) et de leur composants

Séance 4 :
Participation diagramme d’architecture
FRONT : Navigation dans le front-end et pages Login, Register, Market (Acheter & Vendre) et de leur composants

Séance 5 :
FRONT : Communication avec les API back-end des user, card et auth
FRONT : Vue profile

Séance 6 :
FRONT : Communication avec le websocket du chat et création de la page game avec le composant chat

Séance 7 :
FRONT : Tentative de communication avec le websocket du NotifierService (échoué dû à des problèmes CORS du composant StompClient)

Travail outre séance :
FRONT : Communication avec les API store pour vendre et acheter
FRONT : Logout
FRONT : Ajout de la connexion automatique de l’utilisateur connecté au chat

### Anthony :
Dans l’ordre chronologique des séance :
●	Séparation de l’application en microservices et ajout d’une BDD h2 et la persistance des données
●	Analyse et mise en place des ESB dans le microservice Card et User
●	Communication du microservice store avec les microservices Card et User
●	Analyse de la communication avec le serveur de websocket Notification
●	Diagramme de séquence du jeu
●	Aide à l’intégration front/back du store

### Bastien:
Dans l’ordre chronologique des séances:
●	Analyse et réalisation du diagramme d’architecture de base de l’application 
●	Réalisation du diagramme d’architecture de l’évolution de l’application avec des ESB
●	Transformation du backend et séparations en micro-services 
●	Ajout du micro-services d’authentification
●	Mise en place des ESB dans les microservices
●	Réalisation d’une partie du diagramme de séquence de jeu
●	Création de l’ESB notification
●	Ajout d’une websocket pour créer la communication Back / Front 
●	Aide à l’intégration front/back/ESB/websocket et mise en place du service de notification

### Simon
Dans l’ordre chronologique des séances:
●	Participation aux échanges pour l’évolution du diagramme d’architecture avec ESB
●	Création de librairie commune pour les DTO
●	Ajout du microservice store 
●	Réalisation du chat entre deux utilisateurs 
●	Création du système de rooms pour le chat et réutilisable pour le jeu
●	Aide à l’intégration front/back/ESB/websocket

## Lien GIT

Lien du repository git (accessible en public) : https://github.com/BastienKatus/ASI-2SBM


## Éléments réalisés
### Front : 
-	Navigation & NavigationGuards
-	CSS
-	Redux reducer pour les variables globales comme les cartes
-   Les variable de l’utilisateur (argent et carteList) sont modifiés dans la partie front si la requête fonctionne mais on n'interroge pas l’API à chaque changement
-	Vue Login qui se permet de se connecter
-	Vue Register qui permet de créer un utilisateur
-	Composant Card pour l’affichage d’une carte graphique
-	Vue Profile qui affiche les information de l’user connecté et ses cartes
-	Composant CardTable qui affiche un tableau de cartes
-	Composant Market qui CardTable en fonction de si c’est à vendre (possédées par l’utilisateur) ou à acheter (possédées par aucun utilisateur)
-	Boutons qui permettent d’acheter et vendre les cartes
-	Composant Chat qui communique avec la websocket de chat et qui permet de rejoindre une room, l’utilisateur connecté peut choisir un utilisateur avec qui créé une room
-	Vue Game avec le chat
-	Déconnexion

### Back:
-	Séparation de l’application en micro-services
-	Ajout du service d’authentification
-	Mise en place des ESB User / Card / Store
-	Ajout d’une base de données H2 + persistance
-	Ajout d’un service Notification + son ESB
-	Création d’une websocket pour la communication Back / Front
-	Chat en Node.js
-	Communication des différents services, websocket, serveur node.js et Front

## Liste éléments non-réalisés :

## Front :
-	Communication avec l’ESB : Impossible de se connecter à la websocket gérant les ESB en raison d’un problème de headers et de l’utilisation d’un composant react StompClient, trop compliqué à résoudre pour le moment
-	Combat
## Back :
-	Le proxy
-	Logique de jeu / combat


