import React, { useState } from 'react';

const RegistrationForm = () => {
  const [name, setName] = useState('');
  const [surname, setSurname] = useState('');
  const [password, setPassword] = useState('');
  const [rePassword, setRePassword] = useState('');

  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const handleSurnameChange = (e) => {
    setSurname(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleRePasswordChange = (e) => {
    setRePassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if(name !== '' && surname !== '' && password !== '' && rePassword === password){
        console.log('ok')
    }

    // Ajoutez ici la logique pour gérer la soumission du formulaire d'inscription
    console.log('Name:', name);
    console.log('Surname:', surname);
    console.log('Password:', password);
    console.log('Re-entered Password:', rePassword);
    // Réinitialiser les champs après la soumission
    setName('');
    setSurname('');
    setPassword('');
    setRePassword('');
  };

  return (
    <form className="registration-form" onSubmit={handleSubmit}>
      <div>
        <label>
          Name:
          <input type="text" value={name} onChange={handleNameChange} />
        </label>
      </div>
      <div>
        <label>
          Surname:
          <input type="text" value={surname} onChange={handleSurnameChange} />
        </label>
      </div>
      <div>
        <label>
          Password:
          <input type="password" value={password} onChange={handlePasswordChange} />
        </label>
      </div>
      <div>
        <label>
          Re-enter Password:
          <input type="password" value={rePassword} onChange={handleRePasswordChange} />
        </label>
      </div>
      <button type="submit">Register</button>
    </form>
  );
};

export default RegistrationForm;