import React from 'react';
import CardTable from '../../components/CardTable'
import { useSelector } from 'react-redux';
import Card from '../../components/Card'
import { useLocation } from 'react-router-dom';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';

export default function Market(props){

  const dispatch = useDispatch();
  const reducer = useSelector(state => state.reducer)

    const location = useLocation();

      useEffect(() => {
        dispatch({
           type: 'selectCard',
           payload:
           {
               selectedCard: undefined
           }
        })
      }, [location])


    return (
        <>
        <h2>{props.type}</h2>
        <CardTable type={props.type} />
        {reducer.selectedCard !== undefined && <Card card={reducer.selectedCard} />}
        </>
    )
}
