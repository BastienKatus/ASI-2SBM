import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux'
import { useSelector } from 'react-redux';
import root from '../../routes/root';

export default function Home(){

  const reducer = useSelector(state => state.reducer)
    const dispatch = useDispatch();
    useEffect(() => {
        document.title = 'ASI-2SBM';
        fetch('http://localhost:8082/cards')
              .then((response) => response.json())
              .then((data) => {
                dispatch({
                    type: 'cards',
                    payload:
                    {
                        cards: data
                    }
                })
              })
              .catch((error) => {
                console.log('Erreur de requête API : ', error)
              })
    }, []);

    return (
        <>
        <h2>{reducer.user.length !== 0 ? ('Bonjour ' + reducer.user.login) : ('Bonjour')}</h2>
        </>
    )
}
