import React, { useState } from 'react';
import Chat from '../../components/Chat';
import Match from '../../components/Match';
import Card from '../../components/Card';
import { useLocation } from 'react-router-dom';
import { useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import { useSelector } from 'react-redux';
import CardTable from '../../components/CardTable'

const Game = () => {
  const reducer = useSelector(state => state.reducer)
  const [ennemyCard, setEnnemyCard] = useState([]);
  const [myCard, setMyCard] = useState([]);
  const [ennemyName, setEnnemyName] = useState([]);
  const [roomName, setRoomName] = useState([]);
  const [winningUser, setWinningUser] = useState('');
  const [inGame, setInGame] = useState(false);

    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
    console.log(reducer.selectedCard)
        if(reducer.user.length === 0){
            navigate('/')
        }
      }, [location])

      const play = () => {
          if (reducer.selectedCard !== undefined) {
            reducer.socket.emit('joinGame', reducer.user, reducer.selectedCard)

          }
          else {
            alert('Veuillez sélectionner une carte.');
          }
        };

    reducer.socket.on('gameInfo', (roomName, users, cards) => {
      setInGame(true)
      setRoomName(roomName)
      if(reducer.user.id === users[0].id){
        setEnnemyCard(cards[1])
        setMyCard(cards[0])
        setEnnemyName(users[1].login)
      }
      else{
        setEnnemyCard(cards[0])
        setMyCard(cards[1])
        setEnnemyName(users[0].login)
      }
    })

    reducer.socket.on('winner', (user) => {
      setWinningUser(user)
    })

  return (
    <div style={{ display: 'flex' }}>
      {inGame === false &&
      <div style={{ flex: 1, overflowY: 'scroll' }}>
        <h2>Jouer</h2>
        <h4>Séléctionner votre carte :</h4>
        <CardTable type='sell' action='jouer'/>
        <button onClick={play} disabled={reducer.selectedCard === undefined}>
            Jouer
        </button>
        <Match />
      </div>
      }
      {inGame === true &&
      <>
      <p>Room {roomName}</p>
      <p>Vous jouez contre {ennemyName}</p>
      <Card card={myCard} />
      <Card card={ennemyCard} />
      </>
      }
      {inGame === true && winningUser.id === reducer.user.id &&
         <p>Vous avez gagné !</p>
         }
      {inGame === true && winningUser.id !== reducer.user.id &&
         <p>Vous avez perdu !</p>
         }
      <div style={{ width: '500px', backgroundColor: '#f0f0f0' }}>
        <Chat/>
      </div>
    </div>
  );
};

export default Game;
