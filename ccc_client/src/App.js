import Events from "./components/EventComponents/Events";
import AddEvent from './components/EventComponents/AddEvent';
import UpdateEvent from './components/EventComponents/UpdateEvent';
import DeleteEvent from './components/EventComponents/DeleteEvent';
import Users from "./components/UserComponents/Users";
import AddUser from "./components/UserComponents/AddUser";
import UpdateUser from "./components/UserComponents/UpdateUser";
import DeleteUser from "./components/UserComponents/DeleteUser";
import NavBar from "./components/NavBar";
import NotFound from "./components/NotFound";
import Login from "./components/Login";
import Home from "./components/Home";
import EventCalendar from "./components/CalendarComponents/EventCalendar";
import { BrowserRouter as Routes, Route, Switch, Redirect } from "react-router-dom";
import Registration from "./components/Registration";
import { useEffect, useState } from "react";
import AuthContext from "./AuthContext";
import jwtDecode from "jwt-decode";
import Event from "./components/EventComponents/Event";
import './App.css';



const TOKEN = 'jwt_token';

function App() {
  const [init, setInit] = useState(false);
  const [authManager, setAuthManager] = useState({
    user: null,
    roles: null,
    login(token) {
      if (!this.user) {
        const userData = jwtDecode(token);
        localStorage.setItem(TOKEN, token);
        setAuthManager((prevState) => ({ ...prevState, user: userData.sub, roles: userData.authorities }));
      }
    },
    logout() {
      if (this.user) {
        localStorage.removeItem(TOKEN);
        setAuthManager((prevState) => ({ ...prevState, user: null, roles: null }));
      }
    },
    hasRole(role) {
      return this.roles && this.roles.split(',').includes(`ROLE_${role.toUpperCase()}`);
    }
  });

  useEffect(() => {
    const token = localStorage.getItem(TOKEN);
    if (token) {
      authManager.login(token);
    }
    setInit(true);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);



  return (
    <div className="App">

      {init ?
        (<AuthContext.Provider value={authManager} >
          <NavBar />
          <div className="container">
            <Switch>
              <Route exact path="/">
                <Redirect to="/home" />
              </Route>
              <Route path="/home">
                <Home />
              </Route>

              <Route path="/about">
                <About />
              </Route>

              <Route exact path="/event" >
                {authManager.user ? <Events /> : <EventCalendar />}
              </Route>

              <Route path="/event/add" >
                {authManager.user ? <AddEvent /> : <Redirect to="/login" />}
              </Route>

              <Route path="/event/edit/:eventId" >
                {authManager.user ? <UpdateEvent /> : <Redirect to="/login" />}
              </Route>

              <Route exact path="/event/:eventId">
                {authManager.user ? <Event /> : <Redirect to="/calendar" />}
              </Route>

              <Route path="/event/delete/:eventId" >
                {authManager.hasRole('admin') ? <DeleteEvent /> : <NotFound />}
              </Route>

              <Route exact path="/user" >
                {authManager.hasRole('admin') ? <Users /> : <NotFound />}
              </Route>

              <Route path="/user/add" >
                {authManager.user ? <AddUser /> : <Redirect to="/login" />}
              </Route>

              <Route path="/user/edit/:userId" >
                {authManager.user ? <UpdateUser /> : <Redirect to="/login" />}
              </Route>

              <Route path="/user/delete/:userId" >
                {authManager.hasRole('admin') ? <DeleteUser /> : <Redirect to="/login" />}
              </Route>

              <Route path="/login" >
                {authManager.user ? <Redirect to="/" /> : <Login />}
              </Route>

              <Route path="/register" >
                {authManager.user ? <Redirect to="/" /> : <Registration />}
              </Route>

              <Route path="/calendar" >
                <EventCalendar />
              </Route>

              <Route>
                <NotFound />
              </Route>
            </Switch>
          </div>
        </AuthContext.Provider>) : null}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="about" element={<About />} />
      </Routes>
    </div>
  );
}


function About() {
  return (
    <>
      <main>
        <h2 align="center">Who are we?</h2>
        <h3 align="center">Team ETC!</h3>
        <h3 align="center">  That is, <i>E</i>ric, <i>T</i>awfik and <i>C</i>uong.</h3>
        <p>
          Eric is a STEM Career & Technical Education teacher who is changing industries in
          order to work directly in the Information Technology field he loves so much.  He chose
          Dev10 because their paid bootcamp offered the opportunity to launch directly into the
          industry.
        </p>
        <p>
        Tawfik recently graduated from Guilford Community College with an Associates Degree in Computer Programming. 
        He chose to be a Dev10 trainee because as a new college graduate, he was looking for a place where he could develop the 
        skills he learned as well as gain skills in professional tools and techniques.
        </p>
        <p>
        Cuong is recent graduate from The University of Iowa with Bachelor degree in Computer Science and Minor in Mathematic. 
        Upon graduation, he chose to join DEV10 because they can provide Cuong with experiences, necessary tools and skills needs in the workforce.
        </p>
        <p>
          Our team worked hard to make this app useful and easy to use.  We hope you enjoy it!
        </p>
      </main>
    </>
  );
}

export default App;
