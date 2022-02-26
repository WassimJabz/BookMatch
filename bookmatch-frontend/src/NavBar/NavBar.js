import { Link } from 'react-router-dom';
import './NavBar.css';

export default function NavBar(){
    return (
        <div className="bar">
            <div className="logo"><a href="/">BookMatch</a></div>
            <nav>
                <a href="/login">Login</a>
                <a href="/profile">Profile</a>
            </nav>
        </div>
    )
}