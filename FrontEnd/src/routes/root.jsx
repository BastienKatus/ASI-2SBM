import { Outlet, Link } from "react-router-dom";
import { useSelector } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins } from '@fortawesome/free-solid-svg-icons';

function getCircularReplacer() {
  const ancestors = [];
  return function (key, value) {
    if (typeof value !== "object" || value === null) {
      return value;
    }
    // `this` is the object that value is contained in,
    // i.e., its direct parent.
    while (ancestors.length > 0 && ancestors.at(-1) !== this) {
      ancestors.pop();
    }
    if (ancestors.includes(value)) {
      return "[Circular]";
    }
    ancestors.push(value);
    return value;
  };
}

export default function Root(props) {
  const reducer = useSelector(state => state.reducer)
const tempsocket = reducer.socket
  return (
    <>
      <div id="sidebar">
        <nav>
          <ul>
            {reducer.user.length !== 0 ? (
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
            <li className="bottomProfile">
                {reducer.user.length !== 0 && <p>{reducer.user.login} : {reducer.price}<FontAwesomeIcon icon={faCoins} /></p>}

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