import './App.css';
import Login from './views/login/Login';
import Home from './views/home/Home';
import Buy from './views/buy/Buy';
import Sell from './views/sell/Sell';
import Root from './routes/root'
import Register from './views/register/Register'
import React, { useEffect, useState } from 'react';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import {Provider} from 'react-redux'
import store from './redux/store'

function App() {
    const [username, setUsername] = useState('');
    const [money, setMoney] = useState('');
    useEffect(() => {
        document.title = 'ASI-2SBM';
    }, []);
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
    element: <Root username='test' money='test'/>,
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
        path: "Sell",
        element: <Sell />,
    },
    {
        path: "Buy",
        element: <Buy />,
    },
    {
        path: "Register",
        element: <Register />,
    }
  ],
  },
]);

export default App;
