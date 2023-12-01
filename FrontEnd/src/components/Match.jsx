import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import Card from './Card'
import { useLocation } from 'react-router-dom';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from "react-router-dom";

const Match = () => {

const dispatch = useDispatch();
  const reducer = useSelector(state => state.reducer)

    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        if(reducer.user.length === 0){
            navigate('/')
        }
        dispatch({
           type: 'selectCard',
           payload:
           {
               selectedCard: undefined
           }
        })
      }, [location])

  return (
    <div style={{ flex: 1, overflowY: 'scroll' }}>
      {reducer.selectedCard !== undefined && <Card card={reducer.selectedCard} />}
    </div>
  );
};

export default Match;