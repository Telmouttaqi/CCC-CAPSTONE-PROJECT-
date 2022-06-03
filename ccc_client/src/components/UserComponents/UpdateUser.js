import AuthContext from "../../AuthContext";
import Errors from "../Errors";
import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useHistory } from "react-router-dom";


function UpdateUser() {
  const [firstName, setFirstName] = useState('');
  const [middleName, setMiddleName] = useState('');
  const [lastName, setLastName] = useState('');
  const [userAddress, setAddress] = useState('');
  const [userCity, setCity] = useState('');
  const [userState, setState] = useState('');
  const [userZip, setPostalCode] = useState('');
  const [userPhone, setPhone] = useState('');
  const [userEmail, setEmail] = useState('');
  const [errors, setErrors] = useState([]);
  const [init, setInit] = useState(false);
  const history = useHistory();
  const authManager = useContext(AuthContext);
  const { userId } = useParams();

  useEffect(() => {
    const init = {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('jwt_token')}`
      }
    }

    fetch(`${window.API_URL}/user/${userId}`, init)
      .then(response => {
        if (response.status === 200) {
          return response.json();
        }

        if (response.status === 404) {
          history.update('/not-found');
          return null;
        }
        console.log(response);
        return Promise.reject('Something went wrong on the Update User server.');
      })
      .then(body => {
        if (body) {
          setInit(true);
          setFirstName(body.firstName);
          setMiddleName(body.middleName);
          setLastName(body.lastName);
          setAddress(body.userAddress);
          setCity(body.userCity);
          setState(body.userState);
          setPostalCode(body.userZip);
          setPhone(body.userPhone);
          setEmail(body.userEmail);
        }
      })
      .catch(err => console.error(err));
  }, [userId, history]);

  const handleFirstName = (event) => {
    setFirstName(event.target.value);
  }

  const handleMiddleName = (event) => {
    setMiddleName(event.target.value);
  }

  const handleLastName = (event) => {
    setLastName(event.target.value);
  }

  const handleAddress = (event) => {
    setAddress(event.target.value);
  }

  const handleCity = (event) => {
    setCity(event.target.value);
  }

  const handleState = (event) => {
    setState(event.target.value);
  }

  const handlePostalCode = (event) => {
    setPostalCode(event.target.value);
  }

  const handlePhone = (event) => {
    setPhone(event.target.value);
  }

  const handleEmail = (event) => {
    setEmail(event.target.value);
  }

  const handleSubmit = (event) => {
    event.preventDefault();

    const updateUser = {
      userId,
      firstName,
      middleName,
      lastName,
      userAddress,
      userCity,
      userState,
      userZip,
      userPhone,
      userEmail
    };

    const init = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('jwt_token')}`
      },
      body: JSON.stringify(updateUser)
    };

    fetch(`${window.API_URL}/user/${userId}`, init)
      .then(response => {
        switch (response.status) {
          case 204:
            return null;
          case 400:
            return response.json();
          case 403:
            authManager.logout();
            history.push('/login');
            return [];
          default:
            return Promise.reject('Something went wrong on the User server.');
        }
      })
      .then(json => {
        if (json === null) {
          history.push('/user');
        } else {
          setErrors(json);
          console.log(json);
        }
      })
      .catch(err => console.error(err));
  }

  return (
    <>
      {init ? (<>
        <h2 className="mt-5">Edit User - {firstName} {lastName}</h2>
        <form>
          <div className="form-group">
            <label htmlFor="firstName">First Name: </label><br />
            <input className=" col-md-3 mb-3" type="text" id="firstName" name="firstName" value={firstName} onChange={handleFirstName} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="middleName">Middle Name: </label><br />
            <input className=" col-md-3 mb-3" type="text" id="middleName" name="middleName" value={middleName} onChange={handleMiddleName} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="lastName">Last Name: </label><br />
            <input className=" col-md-3 mb-3" type="text" id="lastName" name="lastName" value={lastName} onChange={handleLastName} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="address">Address: </label><br />
            <input className=" col-md-2 mb-3" type="text" id="address" name="address" value={userAddress} onChange={handleAddress} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="city">City: </label><br />
            <input className=" col-md-2 mb-3" type="text" id="city" name="city" value={userCity} onChange={handleCity} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="state">State: </label><br />
            <input className=" col-md-1 mb-3" type="text" id="state" name="state" value={userState} onChange={handleState} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="postalCode">Postal Code: </label><br />
            <input className=" col-md-2 mb-3" type="text" id="postalCode" name="postalCode" value={userZip} onChange={handlePostalCode} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="phone">Phone: </label><br />
            <input className=" col-md-2 mb-3" type="text" id="phone" name="phone" value={userPhone} onChange={handlePhone} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="email">Email: </label><br />
            <input className=" col-md-3 mb-3" type="text" id="email" name="email" value={userEmail} onChange={handleEmail} ></input>
          </div>
          <div className="form-group">
            <button className="btn btn-primary" type="submit" onClick={handleSubmit}>Confirm</button>
            &nbsp;
            <button className="btn btn-secondary" type="button" onClick={() => history.push('/user')}>Cancel</button>
          </div>
        </form>
        <Errors errors={errors} />
      </>) : null}
    </>
  )


}

export default UpdateUser;
