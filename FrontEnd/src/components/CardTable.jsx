import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux'
import { useSelector } from 'react-redux';

const CardTable = (props) => {
  const reducer = useSelector(state => state.reducer)

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

        console.log("reducer ", reducer.user)

        let urlStoreAPI = "http://localhost:8083/" + props.type
        let storeBody = JSON.stringify({"user_id": reducer.login, "card_id": reducer.selectedCard }),

        // Faire appel API
        fetch(urlStoreAPI, {
                      method: "POST",
                      mode: "cors", // no-cors, *cors, same-origin
                      cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
                      credentials: "same-origin", // include, *same-origin, omit
                      headers: {
                        "Content-Type": "application/json",
                        // 'Content-Type': 'application/x-www-form-urlencoded',
                      },
                      redirect: "follow", // manual, *follow, error
                      referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
                      body: storeBody, // body data type must match "Content-Type" header
                    })
                .then((response) => console.log(response))
                .then((data) => console.log("zebi on fait koi la ?")
        // Réinitiliser la liste des cartes
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
          {reducer.cards
          .filter((card) => (
              (props.type === "buy" && !card.isSell) ||
              (props.type === "sell" && reducer.user.cardList.includes(card.id))
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