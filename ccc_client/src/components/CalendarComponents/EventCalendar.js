import { useContext, useEffect, useState } from "react";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import AuthContext from "../../AuthContext";
import { useHistory } from "react-router-dom";

function EventCalendar() {
  const [events, setEvents] = useState([]);
  const authManager = useContext(AuthContext);
  const history = useHistory();

  const getEvents = () => {
    return fetch(`${window.API_URL}/event`)
      .then(response => {
        if (response.status === 200) {
          return response.json()
        }
        return Promise.reject('Sorry! Something went wrong on the server.');
      })
      .then(body => {
        const display = [];
        for (let i = 0; i < body.length; i++) {

          const test = {

            title: body[i].eventName,
            start: body[i].startDate,
            end: body[i].endDate,
            url: `event/${body[i].eventId}`

          }
          display.push(test);

        }

        setEvents(display);
      })
      .catch(err => console.error(err));
  }

  useEffect(() => {
    getEvents();


  },


    []);

  const handleAddSelect = () => {
    history.push('/event/add');
  }

  return (

    <>
      {authManager.user ? <button className="btn btn-success mb-3 mt-4" type="button" onClick={handleAddSelect}>Add Event</button> : null}

      <FullCalendar plugins={[dayGridPlugin]} initialView="dayGridMonth"
        events={events}


      />



    </>
  )

}

export default EventCalendar;