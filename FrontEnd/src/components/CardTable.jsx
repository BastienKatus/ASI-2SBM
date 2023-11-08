import React, { useState, useEffect } from 'react';
import Card from './Card';
import { useDispatch } from 'react-redux'

const CardTable = (props) => {
  const [selectedCard, setSelectedCard] = useState({
    name: '',
  });
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
      console.log(cards)
  }, []);

  const dispatch = useDispatch();

  const handle = () => {
    if (selectedCard.name !== '') {
        dispatch({
            type: props.type,
            payload:{price: parseInt(selectedCard.price)}
        })

    }
    else {
      alert('Veuillez sélectionner une carte.');
    }
  };

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
          .filter((card) => !card.isSell)
          .map((card, index) => (
            <tr
              key={index}
              className={selectedCard.name === card.name ? 'selected' : ''}
              onClick={() => setSelectedCard(card)}
            >
              <td>{card.name}</td>
              <td>{card.description}</td>
              <td>{card.family}</td>
              <td>{card.affinity}</td>
              <td>{card.energy}</td>
              <td>{card.hp}</td>
              <td>{card.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="buy-button-container">
        <button onClick={handle} disabled={!selectedCard}>
            { props.type === 'sell' ? 'Vendre' : 'Acheter' }
        </button>
      </div>
      {selectedCard.name !== '' && <Card card={selectedCard} />}

    </div>
  );
};

export default CardTable;