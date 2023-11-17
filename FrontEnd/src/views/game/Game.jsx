// App.js
import React from 'react';
import Chat from '../../components/Chat';
import { useLocation } from 'react-router-dom';
import { useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import { useSelector } from 'react-redux';

const App = () => {
  const reducer = useSelector(state => state.reducer)

    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        if(reducer.user.length === 0){
            navigate('/')
        }
      }, [location])

  return (
    <div style={{ display: 'flex' }}>
      <div style={{ flex: 1, overflowY: 'scroll' }}>
        {/* Contenu principal de votre application */}
        <h2>Jouer</h2>
      </div>
      <div style={{ width: '500px', backgroundColor: '#f0f0f0' }}>
        {/* Composant de chat sur la droite */}
        <Chat/>
      </div>
    </div>
  );
};

export default App;
