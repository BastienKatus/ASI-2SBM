import { Outlet, Link } from "react-router-dom";
import { useSelector } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins } from '@fortawesome/free-solid-svg-icons';


export default function Root(props) {
  const reducer = useSelector(state => state.reducer)
  return (
    <>
      <div id="sidebar">
        <nav>
          <ul>
            <li>
                <Link to={`/`}>Accueil</Link>
            </li>
            <li>
                <Link to={`/login`}>Se connecter</Link>
            </li>
            <li>
                <Link to={`/register`}>S'inscrire</Link>
            </li>
            <li>
                <Link to={`/sell`}>Vendre</Link>
            </li>
            <li>
                <Link to={`/buy`}>Acheter</Link>
            </li>
            <li className="bottomProfile">
                <p>{reducer.user} : {reducer.price}<FontAwesomeIcon icon={faCoins} /></p>
            </li>
          </ul>
        </nav>
      </div>
      <div id="detail">
        <Outlet />
      </div>
    </>
  );
}