import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from "react-router-dom";

const LoginForm = (props) => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  function handleRouting() {
    navigate("/");
  }

  const handleSubmit = (e) => {
    e.preventDefault();
  fetch('http://localhost:8081/login', {
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
          body: JSON.stringify({"username": username,"password": password}), // body data type must match "Content-Type" header
        })
    .then((response) => response.json())
    .then((data) => {
        dispatch({
            type: 'user',
            payload:{
                username: data,
                price: data.account,
                cardList: data.cardList
            }
        })

        handleRouting()
    })
    setUsername('');
    setPassword('');
  };

  return (
    <form className="login-form" onSubmit={handleSubmit}>
      <div>
        <label>
          Username:
          <input type="text" value={username} onChange={handleUsernameChange} />
        </label>
      </div>
      <div>
        <label>
          Password:
          <input type="password" value={password} onChange={handlePasswordChange} />
        </label>
      </div>
      <button type="submit">Login</button>
    </form>
  );
};

export default LoginForm;