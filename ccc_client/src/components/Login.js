import { useContext, useState } from "react";
import { useHistory } from "react-router-dom";
import AuthContext from "../AuthContext";
import Errors from "./Errors";

const DEFAULT_LOGIN = {
  username: '',
  password: ''
}

function Login() {
  const [credentials, setCredentials] = useState(DEFAULT_LOGIN);
  const [errors, setErrors] = useState([]);
  const authManager = useContext(AuthContext);
  const history = useHistory();

  const handleChange = (event) => {
    const replacmentCredentials = { ...credentials };

    replacmentCredentials[event.target.name] = event.target.value;

    setCredentials(replacmentCredentials);
  }

  const handleSubmit = async (event) => {
    event.preventDefault();

    const init = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(credentials)
    }

    // const resp = await fetch('http://localhost:8080/api/authenticate', init);
    // // eslint-disable-next-line no-unused-vars
    // const json = await resp.json();
    
    fetch(`${window.API_URL}/authenticate`, init)
      .then(resp => {
        if (resp.status === 200) {
          return resp.json();
        }

        if (resp.status === 403) {
          return null;
        }

        return Promise.reject('Something went wrong on the authentication server.');
      })
      .then(json => {
        if (json) {
          authManager.login(json.jwt_token);
          history.push("/home");
        } else {
          setErrors(['Login Fail'])
        }
      })
      .catch(err => console.error(err));
  }

  return (
    <>
      <h2>Login</h2>
      <form>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input className="col-md-3 mb-3" type="text" id="username" name="username" text-align="center" value={credentials.username} onChange={handleChange} ></input>
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input className="col-md-3 mb-3" type="password" id="password" name="password" value={credentials.password} onChange={handleChange} ></input>
        </div>
        <div className="form-group">
          <button className="btn btn-primary" type="submit" onClick={handleSubmit}>Login</button>
        </div>
      </form>
      <Errors errors={errors} />
    </>
  )

}

export default Login;