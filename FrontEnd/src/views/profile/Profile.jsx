import React from 'react';
import { useSelector } from 'react-redux';
import Card from '../../components/Card';
import { useEffect } from 'react';
import { useNavigate } from "react-router-dom";

export default function Profile(){

  const reducer = useSelector(state => state.reducer)

    const navigate = useNavigate();

    const filteredCards = reducer.cards.filter(card => reducer.user.cardList.includes(card.id));

    useEffect(() => {
        if(reducer.user.length === 0){
            navigate('/')
        }
      }, [])

    return (
        <>
            <h2>Profile</h2>
            <p><strong>Login:</strong> {reducer.user.login}</p>
            <p><strong>Argent:</strong> {reducer.user.account}</p>
            <p><strong>Prénom:</strong> {reducer.user.lastName}</p>
            <p><strong>Nom:</strong> {reducer.user.surName}</p>
            <p><strong>Email:</strong> {reducer.user.email}</p>
            <p><strong>Cartes:</strong></p>
            { filteredCards.length === 0 ? (
                <p>Aucune carte</p>
             ) : (
                 <ul className="card-grid">
                      {filteredCards.map(card => (
                          <Card key={card.id} card={card}></Card>
                      ))}
                  </ul>
             )
            }


        </>
    )
}
