import AuthContext from "../../AuthContext";
import Errors from "../Errors";
import { useContext, useEffect, useState, useCallback } from "react";
import { useParams } from "react-router-dom";
import { useHistory } from "react-router-dom";


function UpdateEvent() {
  const [eventName, setEventName] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [capacity, setCapacity] = useState('');
  const [username, setUsername] = useState('');
  const [adminApprove, setAdminApprove] = useState('');
  const [errors, setErrors] = useState([]);
  const [init, setInit] = useState(false);
  const history = useHistory();
  const authManager = useContext(AuthContext);
  const { eventId } = useParams();
  const [category, setCategory] = useState('');
  const [newCategory, setNewCategory] = useState('');
  const [culture, setCulture] = useState('');
  const [newCulture, setNewCulture] = useState('');
  const [categories, setCategories] = useState([]);
  const [cultures, setCultures] = useState([]);
  const [location, setLocation] = useState('');

  const getAll = useCallback(() => {

    const getCat = fetch(`${window.API_URL}/eventCategory`);
    const getCul = fetch(`${window.API_URL}/eventCulture`);
    Promise.all([getCat, getCul]).then(values =>
      Promise.all(values.map(r => r.json()))
    ).then(([ca, cu]) => {
      setCategories(ca);
      setCultures(cu);
    })
  }, []
  );


  useEffect(() => {
    getAll();
  }, [getAll]);


  const handleCategory = (event) => {
    setCategory(event.target.value);
  }

  const handleNewCategory = (event) => {
    setNewCategory(event.target.value);
  }

  const handleCulture = (event) => {
    setCulture(event.target.value);
  }

  const handleNewCulture = (event) => {
    setNewCulture(event.target.value);
  }

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

  const handleEventName = (event) => {
    setEventName(event.target.value);
  }

  const handleLocation = (event) => {
    setLocation(event.target.value);
  }

  const handleStartDate = (event) => {
    setStartDate(event.target.value);
  }

  const handleEndDate = (event) => {
    setEndDate(event.target.value);
  }

  const handleCapacity = (event) => {
    setCapacity(event.target.value);
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    let updateEvent ={};

    if (location !== "" && location !== null) {
      const locList = location.split(",");
      if (locList.length !== 4 || locList[2].trim().length !== 2) {
        setErrors(["Location format: [address, city, state_abbreviation, zipcode]"]);
      } else {
        updateEvent = {
          eventId,
          eventName,
          fullAddress: location,
          startDate,
          endDate,
          capacity,
          category: `${category === "Other" ? newCategory : category}`,
          culture: `${culture === "Other" ? newCulture : culture}`,
          username: `${authManager.hasRole("admin") ? username : authManager.user}`,
          adminApprove
        };
      }
    } else {
      updateEvent = {
        eventId,
        eventName,
        fullAddress: null,
        startDate,
        endDate,
        capacity,
        category: `${category === "Other" ? newCategory : category}`,
        culture: `${culture === "Other" ? newCulture : culture}`,
        username: `${authManager.hasRole("admin") ? username : authManager.user}`,
        adminApprove
      };
    }

    const init = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('jwt_token')}`
      },
      body: JSON.stringify(updateEvent)
    };

    fetch(`${window.API_URL}/event/${eventId}`, init)
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
            return Promise.reject('Something went wrong on the Event server.');
        }
      })
      .then(json => {
        if (json === null) {
          history.push('/event');
        } else {
          setErrors(json);
        }
      })
      .catch(err => console.error(err));
  }




  return (
    <>
      {init ? (<>
        <h2 className="mt-5">Edit Event - {eventName}</h2>
        <form>
          <div className="form-group">
            <label htmlFor="eventName">Event Name:</label><br />
            <input className=" col-md-3 mb-3" type="text" id="eventName" name="eventName" value={eventName} onChange={handleEventName} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="location">Location:</label><br />
            <input className="form-control col-md-3 mb-3" type="text" id="location" name="location" value={location} onChange={handleLocation} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="startDate">Start Date (YYYY-MM-DD):</label><br />
            <input className=" col-md-3 mb-3" type="date" id="startDate" name="startDate" value={startDate} onChange={handleStartDate} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="endDate">End Date (YYYY-MM-DD):</label><br />
            <input className=" col-md-2 mb-3" type="date" id="endDate" name="endDate" value={endDate} onChange={handleEndDate} ></input>
          </div>
          <div className="form-group">
            <label htmlFor="capacity">Capacity:</label><br />
            <input className=" col-md-1 mb-3" type="number" id="capacity" name="capacity" value={capacity} onChange={handleCapacity} ></input>
          </div>

          <div className="form-group">
            <label htmlFor="category">Category:</label><br />
            <select className="form-control col-md-2 mb-3" name="category" value={category} id="category" onChange={handleCategory}>
              <option></option>
              {categories.map((cate, i) => (
                <option key={i}>{`${cate.categoryName}`}</option>
              ))}
              <option>Other</option>
            </select>

            {category === "Other" ? <input className="form-control col-md-2 mb-3" type="text" id="newCategory" name="newCategory" value={newCategory} onChange={handleNewCategory} ></input> : null}
          </div>

          <div className="form-group">
            <label htmlFor="culture">Culture:</label><br />
            <select className="form-control col-md-2 mb-3" name="culture" id="culture" value={culture} onChange={handleCulture}>
              <option></option>
              {cultures.map((cult, i) => (
                <option key={i}>{`${cult.cultureName}`}</option>
              ))}
              <option>Other</option>
            </select>
            {culture === "Other" ? <input className="form-control col-md-2 mb-3" type="text" id="newCulture" name="newCulture" value={newCulture} onChange={handleNewCulture} ></input> : null}
          </div>

          <div className="form-group">
            <button className="btn btn-primary" type="submit" onClick={handleSubmit}>Confirm</button>
            &nbsp;
            <button className="btn btn-secondary" type="button" onClick={() => history.push('/event')}>Cancel</button>
          </div>
        </form>
        <Errors errors={errors} />
      </>) : null}
    </>
  )


}

export default UpdateEvent;