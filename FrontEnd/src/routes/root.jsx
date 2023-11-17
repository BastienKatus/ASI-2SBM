import { Outlet, Link } from "react-router-dom";
import { useSelector } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins } from '@fortawesome/free-solid-svg-icons';
import { useDispatch } from 'react-redux'
import { useNavigate } from "react-router-dom";

export default function Root(props) {
  const reducer = useSelector(state => state.reducer)
    const dispatch = useDispatch();
    const navigate = useNavigate();


  const logout = () => {
     navigate('/login')
    dispatch({
         type: 'user',
         payload:
         {
             user: []
         }
     })
  };

  return (
    <>
      <div id="sidebar">
        <nav>
          <ul>
            {reducer.user && reducer.user.length !== 0 ? (
                    <>
                    <li>
                        <Link to={`/`}>Accueil</Link>
                    </li>
                    <li>
                        <Link to={`/sell`}>Vendre</Link>
                    </li>
                    <li>
                        <Link to={`/buy`}>Acheter</Link>
                    </li>
                    <li>
                        <Link to={`/profile`}>Profile</Link>
                    </li>
                    <li>
                        <Link to={`/game`}>Jouer</Link>
                    </li>
                    </>
                ) : (
                    <>
                    <li>
                        <Link to={`/`}>Accueil</Link>
                    </li>
                    <li>
                        <Link to={`/login`}>Se connecter</Link>
                    </li>
                    <li>
                        <Link to={`/register`}>S'inscrire</Link>
                    </li>
                    </>
                )
            }
            {reducer.user && reducer.user.length !== 0 && <li className="bottomProfile">
                <p>{reducer.user.login} : {reducer.price}<FontAwesomeIcon icon={faCoins} /></p>
                <button onClick={logout}>Se Déconnecter</button>
            </li>}
          </ul>
        </nav>
      </div>
      <div id="detail">
        <Outlet />
      </div>
    </>
  );
}