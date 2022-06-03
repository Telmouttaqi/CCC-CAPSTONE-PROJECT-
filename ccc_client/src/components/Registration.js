import { useState } from "react";
import { useHistory } from "react-router-dom";
import Errors from "./Errors";

const DEFAULT_LOGIN = {
    firstName: '',
    lastName: '',
    address: '',
    city: '',
    state: '',
    postalCode: '',
    phone: '',
    email: '',
    username: '',
    password: '',
    confirmation: ''
}

function Registration() {

    const [credentials, setCredentials] = useState(DEFAULT_LOGIN);
    const [errors, setErrors] = useState([]);

    const history = useHistory();

    const handleChange = (event) => {
        const replacmentCredentials = { ...credentials };

        replacmentCredentials[event.target.name] = event.target.value;

        setCredentials(replacmentCredentials);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (credentials.password !== credentials.confirmation) {
            setErrors(['Password and Password Confirmation do not match']);
            return;
        }

        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        }
        
        

        fetch(`${window.API_URL}/create_account`, init)
            .then(resp => {
                if (resp.status === 201) {
                    return null;
                }

                if (resp.status === 400) {
                    return resp.json();
                }

                return Promise.reject('Something went wrong on the server.');
            })
            .then(json => {
                if (json) {
                    setErrors(json)
                } else {
                    history.push("/login");
                }


            })
            .catch(err => console.error(err));
    }

    return (
        <>
            <h2>Registration</h2>
            <form>
                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="text" id="firstName" name="firstName" placeholder="First Name" value={credentials.firstName} onChange={handleChange} required></input>
                </div>
                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="text" id="middleName" name="middleName" placeholder="Middle Name" value={credentials.middleName} onChange={handleChange} ></input>
                </div>
                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="text" id="lastName" name="lastName" placeholder="Last Name" value={credentials.lastName} onChange={handleChange} required></input>
                </div>
                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="text" id="userAddress" name="userAddress" placeholder="Address" value={credentials.userAddress} onChange={handleChange} required ></input>
                </div>
                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="text" id="userCity" name="userCity" placeholder="City" value={credentials.userCity} onChange={handleChange} required></input>
                </div>
                <div class="col-md-4">
                    {/* <input className="form-control" type="text" id="inputState" name="state" placeholder="State" value={state} onChange={handleState} required ></input> */}
                    {/* <input className=" col-md-1 mb-3" type="text" id="userState" name="userState" placeholder="State" value={credentials.userState} onChange={handleChange} required ></input> */}
                    <select id="inputState" className=" col-md-2 mb-3" class="form-select" size="1" name="userState" placeholder="State" value={credentials.userState} onChange={handleChange}>
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

                {/* <div className="form-group">
                    <input className=" col-md-1 mb-3" type="text" id="userState" name="userState" placeholder="State" value={credentials.userState} onChange={handleChange} required ></input>
                </div> */}
                <div className="form-group">
                    <input className=" col-md-1 mb-3" type="text" id="userZip" name="userZip" placeholder="Zip" value={credentials.userZip} onChange={handleChange} required></input>
                </div>
                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="text" id="userPhone" name="userPhone" placeholder="Phone" value={credentials.userPhone} onChange={handleChange} required></input>
                </div>
                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="text" id="userEmail" name="userEmail" placeholder="Email" value={credentials.userEmail} onChange={handleChange} required></input>
                </div>

                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="text" id="username" name="username" placeholder="Username" value={credentials.username} onChange={handleChange} required></input>
                </div>
                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="password" id="password" name="password" placeholder="Password" value={credentials.password} onChange={handleChange} required></input>
                </div>
                <div className="form-group">
                    <input className=" col-md-3 mb-3" type="password" id="confirmation" name="confirmation" placeholder="Confirm Password" value={credentials.confirmation} onChange={handleChange} required></input>
                </div>
                <div className="form-group">
                    <button className="btn btn-primary" type="submit" onClick={handleSubmit}>Register</button>
                </div>
            </form>
            <Errors errors={errors} />
        </>
    )
}

export default Registration;