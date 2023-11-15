import './App.css';
import Login from './views/login/Login';
import Home from './views/home/Home';
import Market from './views/market/Market';
import Profile from './views/profile/Profile';
import Game from './views/game/Game';
import Root from './routes/root'
import Register from './views/register/Register'
import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import {Provider} from 'react-redux'
import store from './redux/store'
import io from 'socket.io-client';

function App() {

  const socket = io('localhost:4000');
  const dispatch = useDispatch();

    dispatch({
        type: 'socket',
        payload:{
            socketio : socket
        }
    })

    return (
        <React.StrictMode>
            <Provider store={store}>
                <RouterProvider router={router} />
            </Provider>
        </React.StrictMode>
    );
}

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root/>,
    children: [
    {
        path: "/",
        element: <Home />,
    },
    {
        path: "/login",
        element: <Login />,
    },
    {
        path: "/sell",
        element: <Market type="sell" />,
    },
    {
        path: "/buy",
        element: <Market type="buy" />,
    },
    {
        path: "/register",
        element: <Register />,
    },
    {
        path: "/profile",
        element: <Profile />,
    },
    {
        path: "/game",
        element: <Game />,
    },
  ],
  },
]);

export default App;
