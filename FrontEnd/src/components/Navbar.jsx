import { NavLink, Link} from "react-router-dom"
import './Navbar.css'
import { useState } from "react"
import { useNavigate } from "react-router-dom";

function Navbar({setLogin, login}){
    const navigate = useNavigate();

    const handleLogout = ()=>{
        setLogin(false);
        localStorage.removeItem("token")
        navigate("/login")
    }

    return (
        <>
            <div className="nav-bar">
                <h2>MentorConnect</h2>

                <div className="nav-bar-links">
                    <NavLink to='/home'>Home</NavLink>
                    <NavLink to='/mentors'>Mentors</NavLink>
                    <NavLink to='/sessions'>Sessions</NavLink>
                    <Link onClick={handleLogout}>Logout</Link>
                </div>
            </div>
        </>
    )
}

export default Navbar