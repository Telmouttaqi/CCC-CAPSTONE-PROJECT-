import { useContext, useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import AuthContext from "../../AuthContext";
import AddEvent from "./AddEvent";
import DeleteEvent from "./DeleteEvent";

const VIEW = {
  LIST: 'list',
  ADD: 'add',
  EDIT: 'edit',
  DELETE: 'delete'
};

function Events() {
  const [errors, setErrors] = useState([]);
  const [events, setEvents] = useState([]);
  const [event, setEvent] = useState({});
  const [view, setView] = useState(VIEW.LIST);
  const history = useHistory();
  const authManager = useContext(AuthContext);

  const init = {
    method: 'GET',
  }

  const getEvents = () => {

    return fetch(`${window.API_URL}/event`, init)
      .then(response => {
        if (response.status === 200) {
          return response.json()
        }
        return Promise.reject('Sorry! Something went wrong on the server.');
      })
      .then(body => {
        setEvents(body);
      })
      .catch(err => console.error(err));
  }

  useEffect(() => {
    getEvents();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleAdd = (newEvent) => {
    setEvents([...events, newEvent]);
    setView(VIEW.LIST);
  }

  const handleAddSelect = () => {
    history.push('/event/add');
  }

  const handleDelete = (eventId) => {
    const filteredList = [...events].filter(td => td.eventId !== eventId);
    setEvents(filteredList);
    setEvent({});
    setView(VIEW.LIST);
  }

  const handleEditSelect = (event) => {
    history.push(`/event/edit/${event.eventId}`);
  }

  const handleDeleteSelect = (event) => {
    history.push(`/event/delete/${event.eventId}`);
  }

  const handleCancel = () => {
    setEvent({});
    setView(VIEW.LIST);
  }
  const handleRsvpSelect = (event) => {

    const newRsvp = {
      username: authManager.user,
      eventId: event.eventId,
      approved: true
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
      <h2 className="mt-5">Event List</h2>
      {authManager.user ? <button className="btn btn-success mb-3 mt-4" type="button" onClick={handleAddSelect}>Add Event</button> : null}

      <table className="table table-md">
        <thead>
          <tr>
            <th scope="col"></th>
            {/* <th scope="col">ID</th> */}
            <th scope="col">Event Name</th>
            <th scope="col">Event Address</th>
            <th scope="col">Date</th>
            <th scope="col">Capacity</th>
            <th scope="col">Category</th>
            <th scope="col">Culture</th>
            {/* <th scope="col">User ID #</th> */}

          </tr>
        </thead>
        <tbody>
          {events.map((td, i) => (
            <tr key={td.eventId}>
              <td>

                {authManager.user !== td.username ?
                  <button className="btn btn-primary btn-sm" type="button" onClick={() => handleRsvpSelect(td)} >RSVP</button> : null
                }

                &nbsp;
                {authManager.user === td.username || authManager.hasRole('admin') ?
                  <button className="btn btn-info btn-sm" type="button" onClick={() => handleEditSelect(td)} >Edit</button> : null}

                &nbsp;
                {authManager.hasRole('admin') ?
                  <button className="btn btn-danger btn-sm" type="button" onClick={() => handleDeleteSelect(td)} >Delete</button> : null}




                {/* <button className="btn btn-warning" type="button" onClick={() => handleEditSelect(td)} >Edit</button>
                  &nbsp;

                <button className="btn btn-danger" type="button" onClick={() => handleDeleteSelect(td)} >Delete</button> */}

              </td>

              {/* <td>
                &nbsp;
                {i + 1}
              </td> */}
              <td>
                {td.eventName}
              </td>
              <td>
                {td.fullAddress}
              </td>
              <td>
                {td.endDate > td.startDate ? `${td.startDate} to ${td.endDate}` : td.startDate}
                {/* {td.startDate} to {td.endDate} */}
              </td>
              <td>
                {td.capacity}
              </td>
              <td>
                {td.category}
              </td>
              <td>
                {td.culture}
              </td>
              <td></td>
              <td>
                {td.userId}
              </td>

            </tr>
          ))}
        </tbody>
      </table>
      {view === VIEW.ADD ? <AddEvent handleAdd={handleAdd} handleCancel={handleCancel} /> : null}
      {view === VIEW.DELETE ? <DeleteEvent event={event} handleDelete={handleDelete} handleCancel={handleCancel} /> : null}
    </>
  )
}

export default Events;