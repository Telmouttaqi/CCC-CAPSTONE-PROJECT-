import { useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../AuthContext";

function NavBar() {

    const authManager = useContext(AuthContext);
    const handleLogout = () => {
        authManager.logout();
    }

    return (
        <nav className="navbar fixed-top navbar-expand-lg navbar-dark bg-primary">
            <span className="navbar-brand" >Cultural Connections Calendar</span>
            <div className="navbar-collapse">
                <ul className="navbar-nav mr-auto">

                    <li className="nav-item">
                        <Link className="nav-link" to="/Home">Home</Link>
                    </li>

                    <li className="nav-item" >
                        <Link className="nav-link" to="/about">About</Link>
                    </li>

                    <li className="nav-item">
                        {authManager.user ? <Link className="nav-link" to="/event">Events</Link> : null}
                    </li>

                    <li className="nav-item">
                        <Link className="nav-link" to="/calendar">Calendar</Link>
                    </li>

                    <li className="nav-item">
                    {authManager.hasRole("admin") ? <Link className="nav-link" to="/user">Users</Link> : null}
                    </li>

                    {authManager.user ? (
                        <li className="nav-item">
                            <button className="btn btn-info" type="button" onClick={handleLogout}>Logout</button>
                        </li>
                    )
                        : (
                            <>
                                <li className="nav-item" >
                                    <Link className="nav-link" to="/login">Login</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/register">Register</Link>
                                </li>
                            </>
                        )}
                </ul>
            </div>
            {authManager.user ? <span className="text-light navbar-text" >Hello, {authManager.user}</span> : null}
        </nav>
    );
}

export default NavBar;