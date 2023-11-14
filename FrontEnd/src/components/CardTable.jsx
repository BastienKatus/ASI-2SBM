import React, { useState, useEffect } from 'react';
import Card from './Card';
import { useDispatch } from 'react-redux'
import { useSelector } from 'react-redux';

const CardTable = (props) => {
  const reducer = useSelector(state => state.reducer)
  const [cards, setCards] = useState([]);
  useEffect(() => {
      fetch('http://localhost:8082/cards')
      .then((response) => response.json())
      .then((data) => {
        setCards(data)
      })
      .catch((error) => {
        console.log('Erreur de requête API : ', error)
      })
  }, []);

  const dispatch = useDispatch();

  const handle = () => {
    if (reducer.selectedCard !== undefined) {
        dispatch({
            type: props.type,
            payload:
            {
                price: parseInt(reducer.selectedCard.price)
            }
        })

    }
    else {
      alert('Veuillez sélectionner une carte.');
    }
  };

  const handleSelectCard = (card) => {
       dispatch({
           type: 'selectCard',
           payload:
           {
               selectedCard: card
           }
       })
  }

  return (
    <div className="card-table-container">
      <table className="card-table">
        <thead>
          <tr>
            <th>Nom</th>
            <th>Description</th>
            <th>Famille</th>
            <th>Affinité</th>
            <th>Énergie</th>
            <th>HP</th>
            <th>Prix</th>
          </tr>
        </thead>
        <tbody>
          {cards
          .filter((card) => (
              (props.type === "buy" && !card.isSell) ||
              (props.type === "sell" && reducer.cards.includes(card.id))
            ))
          .map((card, index) => (
            <tr
              key={index}
              className={reducer.selectedCard && reducer.selectedCard.name === card.name ? 'selected' : ''}
              onClick={() => handleSelectCard(card)}
            >
              <td>{card.name}</td>
              <td>{card.description}</td>
              <td>{card.family}</td>
              <td>{card.affinity}</td>
              <td>{card.energy}</td>
              <td>{card.hp}</td>
              <td>{card.price}</td>
              <td>{card.isSell}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="buy-button-container">
        <button onClick={handle} disabled={reducer.selectedCard === undefined}>
            { props.type === 'sell' ? 'Vendre' : 'Acheter' }
        </button>
      </div>
    </div>
  );
};

export default CardTable;