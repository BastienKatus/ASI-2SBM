import React, { useState } from 'react';
import Card from './Card';
import { useDispatch } from 'react-redux'
import buy from '../redux/actions'

const CardTable = (props) => {
  const [selectedCard, setSelectedCard] = useState({
    name: '',
  });

  const dispatch = useDispatch();

  const data = [
    { name: 'Card1', description: 'Description1', family: 'Family1', affinity: 'Affinity1', energy: 100, hp: 200, price: 10 },
    { name: 'Card2', description: 'Description2', family: 'Family2', affinity: 'Affinity2', energy: 150, hp: 250, price: 20 },
    { name: 'Card3', description: 'Description3', family: 'Family3', affinity: 'Affinity3', energy: 120, hp: 220, price: 15 },
    // Ajoutez plus d'éléments au besoin
  ];

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
          {data.map((card, index) => (
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