import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from "react-router-dom";

const RegistrationForm = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [lastName, setLastName] = useState('');
  const [surName, setSurName] = useState('');
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');
  const [rePassword, setRePassword] = useState('');
  const [email, setEmail] = useState('');

  const handleLastNameChange = (e) => {
    setLastName(e.target.value);
  };

  const handleSurNameChange = (e) => {
    setSurName(e.target.value);
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handleLoginChange = (e) => {
    setLogin(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleRePasswordChange = (e) => {
    setRePassword(e.target.value);
  };

  function handleRouting() {
    navigate("/login");
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8081/register', {
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
              body: JSON.stringify({"login": login,"pwd": password, "lastName": lastName, "surName": surName, "email": email}), // body data type must match "Content-Type" header
            })
        .then(response => response.json())
        .then(json => console.log('Réponse de Notifier Service : ', json, '. Votre Utilisateur a été créé, vous pouvez vous connecter'))
//         })
    handleRouting()
    // Réinitialiser les champs après la soumission
    setSurName('');
    setLastName('');
    setPassword('');
    setRePassword('');
    setEmail('');
  };

  return (
    <form className="registration-form" onSubmit={handleSubmit}>
      <div>
        <label>
          Nom:
          <input type="text" value={lastName} onChange={handleLastNameChange} />
        </label>
      </div>
      <div>
        <label>
          Prénom:
          <input type="text" value={surName} onChange={handleSurNameChange} />
        </label>
      </div>
      <div>
        <label>
          Email:
          <input type="email" value={email} onChange={handleEmailChange} />
        </label>
      </div>
      <div>
        <label>
          Login:
          <input type="text" value={login} onChange={handleLoginChange} />
        </label>
      </div>
      <div>
        <label>
          Mot de Passe:
          <input type="password" value={password} onChange={handlePasswordChange} />
        </label>
      </div>
      <div>
        <label>
          Confirmation Mot de Passe:
          <input type="password" value={rePassword} onChange={handleRePasswordChange} />
        </label>
      </div>
      <button type="submit">Register</button>
    </form>
  );
};

export default RegistrationForm;