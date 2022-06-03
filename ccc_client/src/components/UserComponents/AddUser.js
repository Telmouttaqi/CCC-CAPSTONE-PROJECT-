import { useState, useContext } from "react";
import { useHistory } from 'react-router-dom';
import AuthContext from "../../AuthContext";
import Errors from "../Errors";

function AddUser({ handleAdd, handleCancel }) {
  const [firstName, setFirstName] = useState('');
  const [middleName, setMiddleName] = useState('');
  const [lastName, setLastName] = useState('');
  const [userAddress, setUserAddress] = useState('');
  const [userCity, setUserCity] = useState('');
  const [userState, setUserState] = useState('');
  const [userZip, setUserZip] = useState('');
  const [userPhone, setUserPhone] = useState('');
  const [userEmail, setUserEmail] = useState('');
  const [errors, setErrors] = useState([]);

  const history = useHistory();
  const authManager = useContext(AuthContext);

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
    setUserAddress(event.target.value);
  }

  const handleCity = (event) => {
    setUserCity(event.target.value);
  }

  const handleState = (event) => {
    setUserState(event.target.value);
  }

  const handlePostalCode = (event) => {
    setUserZip(event.target.value);
  }

  const handlePhone = (event) => {
    setUserPhone(event.target.value);
  }

  const handleEmail = (event) => {
    setUserEmail(event.target.value);
  }

  const handleSubmit = (event) => {
    event.preventDefault();

    const newUser = {
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
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('jwt_token')}`
      },
      body: JSON.stringify(newUser)
    };

    
    fetch(`${window.API_URL}/user`, init)
      .then(response => {
        switch (response.status) {
          case 201:
          case 400:
            return response.json();
          case 403:
            authManager.logout();
            history.push('/login');
            break;
          default:
            return Promise.reject('Something went wrong on the User server.');
        }
      })
      .then(json => {
        if (json.userId > 0) {
          history.push('/user');
        } else {
          setErrors(json);
        }
      })
      .catch(err => console.error(err));
  }

  return (
    <>
      <h2 className="mt-5">Add User</h2>
      <form class="row g-3">
        <div class="col-md-4">
          <input className="form-control" type="text" id="firstName" name="firstName" placeholder="First Name" value={firstName} onChange={handleFirstName} required ></input>
        </div>
        <div class="col-md-4">
          <input className="form-control" type="text" id="middleName" name="middleName" placeholder="Middle Name" value={middleName} onChange={handleMiddleName} ></input>
        </div>
        <div class="col-md-4">
          <input className="form-control" type="text" id="lastName" name="lastName" placeholder="Last Name" value={lastName} onChange={handleLastName} required></input>
        </div>
        <div class="col-12">
          <input className="form-control" type="text" id="address" name="address" placeholder="Address" value={userAddress} onChange={handleAddress} required ></input>
        </div>
        <div class="col-md-6">
          <input className="form-control" type="text" id="city" name="city" placeholder="City" value={userCity} onChange={handleCity} required ></input>
        </div>
        <div class="col-md-4">
          {/* <input className="form-control" type="text" id="inputState" name="state" placeholder="State" value={state} onChange={handleState} required ></input> */}
          <select id="inputState" class="form-select" size="1" onChange={handleState}>
            <option></option>
            <option value="AK">AK</option>
            <option value="AL">AL</option>
            <option value="AR">AR</option>
            <option value="AZ">AZ</option>
            <option value="CA">CA</option>
            <option value="CO">CO</option>
            <option value="CT">CT</option>
            <option value="DC">DC</option>
            <option value="DE">DE</option>
            <option value="FL">FL</option>
            <option value="GA">GA</option>
            <option value="HI">HI</option>
            <option value="IA">IA</option>
            <option value="ID">ID</option>
            <option value="IL">IL</option>
            <option value="IN">IN</option>
            <option value="KS">KS</option>
            <option value="KY">KY</option>
            <option value="LA">LA</option>
            <option value="MA">MA</option>
            <option value="MD">MD</option>
            <option value="ME">ME</option>
            <option value="MI">MI</option>
            <option value="MN">MN</option>
            <option value="MO">MO</option>
            <option value="MS">MS</option>
            <option value="MT">MT</option>
            <option value="NC">NC</option>
            <option value="ND">ND</option>
            <option value="NE">NE</option>
            <option value="NH">NH</option>
            <option value="NJ">NJ</option>
            <option value="NM">NM</option>
            <option value="NV">NV</option>
            <option value="NY">NY</option>
            <option value="OH">OH</option>
            <option value="OK">OK</option>
            <option value="OR">OR</option>
            <option value="PA">PA</option>
            <option value="RI">RI</option>
            <option value="SC">SC</option>
            <option value="SD">SD</option>
            <option value="TN">TN</option>
            <option value="TX">TX</option>
            <option value="UT">UT</option>
            <option value="VA">VA</option>
            <option value="VT">VT</option>
            <option value="WA">WA</option>
            <option value="WI">WI</option>
            <option value="WV">WV</option>
            <option value="WY">WY</option>
          </select>
        </div>
        <div class="col-md-2">
          <input className="form-control" type="text" id="postalCode" name="postalCode" placeholder="Postal Code" value={userZip} onChange={handlePostalCode} required></input>
        </div>
        <div class="col-md-6">
          <input className="form-control" type="text" id="phone" name="phone" placeholder="Phone" value={userPhone} onChange={handlePhone} required></input>
        </div>
        <div class="col-md-6">
          <input className="form-control" type="text" id="email" name="email" placeholder="Email" value={userEmail} onChange={handleEmail} required ></input>
        </div>
        <div className="form-group">
          <button className="btn btn-primary" type="submit" onClick={handleSubmit}>Add User</button>
          &nbsp;
          <button className="btn btn-secondary" type="button" onClick={() => history.push('/user')}>Cancel</button>
        </div>
      </form>
      <Errors errors={errors} />
    </>
  )

}

export default AddUser;