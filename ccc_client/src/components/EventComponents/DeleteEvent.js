import AuthContext from "../../AuthContext";
import Errors from "../Errors";
import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useHistory } from "react-router-dom";


function DeleteEvent() {
  const history = useHistory();
  const [event, setEvent] = useState([]);
  const authManager = useContext(AuthContext);
  const [init, setInit] = useState(false);
  const [errors, setErrors] = useState([]);
  const { eventId } = useParams();

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
        return Promise.reject('Something went wrong on the delete server.');
      })
      .then(body => {
        if (body) {
          setInit(true);
          setEvent(body);
        } else {
          setErrors(body);
        }
      })
      .catch(err => console.error(err));
  }, [eventId, history]);


  const handleSubmit = () => {
    const init = {
      method: 'DELETE',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt_token')}`
      }
    }

    fetch(`${window.API_URL}/event/${event.eventId}`, init)
      .then(response => {
        if (response.status === 204) {
          history.push("/event")
          return;
        } else if (response.status === 403) {
          authManager.logout();
          history.push('/login');
        }
        return Promise.reject('Whoops! Something went wrong.');
      })
      .catch(err => console.error(err));
  }

  return (
    <>
      {init ? (<>
        <h2>Event Delete</h2>
        <div className="alert alert-danger">
          <div>Are you sure you want to delete this event?</div>
          <div>Event: {event.eventName} {event.userName}</div>
        </div>
        <button className="btn btn-primary" type="button" onClick={handleSubmit}>Delete</button>
        &nbsp;
        <button className="btn btn-secondary" type="button" onClick={() => history.push("/event")}>Cancel</button>
        <Errors errors={errors} />
      </>) : null}
    </>
  );

}

export default DeleteEvent;