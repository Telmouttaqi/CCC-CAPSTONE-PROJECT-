import AuthContext from "../../AuthContext";
import Errors from "../Errors";
import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useHistory } from "react-router-dom";

function Event() {
    const [eventName, setEventName] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [capacity, setCapacity] = useState('');
    const { eventId } = useParams();
    const [username, setUsername] = useState('');
    const [adminApprove, setAdminApprove] = useState('');
    const [errors, setErrors] = useState([]);
    const [init, setInit] = useState(false);
    const history = useHistory();
    const [category, setCategory] = useState('');
    const [culture, setCulture] = useState('');
    const [location, setLocation] = useState('');
    const authManager = useContext(AuthContext);


    useEffect(() => {
        const init = {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem('jwt_token')}`
            }
        }

        fetch(`${window.API_URL}/event/${eventId}`, init)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                }

                if (response.status === 404) {
                    history.update('/not-found');
                    return null;
                }
                console.log(response);
                return Promise.reject('Something went wrong on the Update Event server.');
            })
            .then(body => {
                if (body) {
                    setInit(true);
                    setEventName(body.eventName);
                    setLocation(body.fullAddress);
                    setStartDate(body.startDate);
                    setEndDate(body.endDate);
                    setCapacity(body.capacity);
                    setCategory(body.category);
                    setCulture(body.culture);
                    setUsername(body.username);
                    setAdminApprove(body.adminApprove);
                }
            })
            .catch(err => console.error(err));
    }, [eventId, history]);

    const handleEditSelect = () => {
        history.push(`/event/edit/${eventId}`);
    }

    const handleDeleteSelect = () => {
        history.push(`/event/delete/${eventId}`);
    }
    const handleRsvpSelect = () => {

        const newRsvp = {
            username: authManager.user,
            eventId,
            approved : true
        };

        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem('jwt_token')}`
            },
            body: JSON.stringify(newRsvp)
        };
        
        fetch(`${window.API_URL}/rsvp`, init)
            .then(response => {
                switch (response.status) {
                    case 201:
                    case 400:
                        return response.json();
                    case 403:
                        authManager.logout();
                        history.push('/login');
                        return [];

                    default:
                        return Promise.reject('Something went wrong on the server.');
                }
            })
            .then(json => {
                if (json.eventId > 0) {
                    history.push('/calendar');
                } else {
                    setErrors(json);
                }
            })
            .catch(err => console.error(err));
    }

    return (
        <>
            {init ? (<>
                <h2 className="mt-5">{eventName}</h2>
                <div>
                    <div>Location: <strong> {location == null ? "N/A" : location} </strong> </div>
                    <div>Date: <strong> {endDate > startDate ? `${startDate} to ${endDate}` : startDate}
 </strong> </div>
                    <div>Capacity: <strong> {capacity} </strong> </div>
                    <div>Category: <strong> {category} </strong> </div>
                    <div>Culture: <strong> {culture} </strong></div>
                    &nbsp;
                </div>


                &nbsp;

                {authManager.user === username ?
                    <button className="btn btn-info" type="button" onClick={() => handleEditSelect()} >Edit</button> :
                    <button className="btn btn-primary" type="button" onClick={() => handleRsvpSelect()} >RSVP</button>
                }
                &nbsp;

                {authManager.hasRole('admin') ? <button className="btn btn-danger" type="button" onClick={() => handleDeleteSelect()} >Delete</button> : null}
                <button className="btn btn-secondary" type="button" onClick={() => history.push("/calendar")}>Cancel</button>
                <Errors errors={errors} />
            </>) : null}
        </>
    )

}

export default Event;