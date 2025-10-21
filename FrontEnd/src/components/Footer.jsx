import { NavLink } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faHome } from '@fortawesome/free-solid-svg-icons';
import { faGithub, faLinkedin } from '@fortawesome/free-brands-svg-icons';

import './Footer.css'
function Footer(){
    return (
        <>
            <div className="footer-bar">
                <div className="footer-bar-links1">
                    <NavLink to='/about'>About</NavLink>
                    <NavLink to='/t&c'>Terms & Conditions</NavLink>
                    <NavLink to='/references'>References</NavLink>
                </div>

                <div className="footer-bar-links2">
                    <NavLink to='https://linkedin.com/in/areef035'><FontAwesomeIcon icon={faLinkedin}/>LinkedIn</NavLink>
                    <NavLink to='https://github.com/areef4400'><FontAwesomeIcon icon={faGithub}/> GitHub</NavLink>
                </div>
            </div>
        </>
    )
}

export default Footer