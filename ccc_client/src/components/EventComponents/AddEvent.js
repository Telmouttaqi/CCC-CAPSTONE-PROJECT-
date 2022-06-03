import { useState, useContext, useEffect, useCallback } from "react";
import { useHistory } from 'react-router-dom';
import AuthContext from "../../AuthContext";
import Errors from "../Errors";

function AddEvent({ handleAdd, handleCancel }) {
  const [eventName, setEventName] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [capacity, setCapacity] = useState('');
  const [errors, setErrors] = useState([]);

  const [category, setCategory] = useState('n');
  const [newCategory, setNewCategory] = useState('');
  const [culture, setCulture] = useState('');
  const [newCulture, setNewCulture] = useState('');
  const [categories, setCategories] = useState([]);
  const [cultures, setCultures] = useState([]);
  const [location, setLocation] = useState('');
  const history = useHistory();
  const authManager = useContext(AuthContext);

  

  const getAll = useCallback( () => {
    const getCat = fetch(`${window.API_URL}/eventCategory`);
    const getCul = fetch(`${window.API_URL}/eventCulture`);
    Promise.all([getCat, getCul]).then(values =>
      Promise.all(values.map(r => r.json()))
    ).then(([ca, cu]) => {
      setCategories(ca);
      setCultures(cu);
    })},[]
  );
  

  useEffect(() => {
    getAll();
  },[getAll]);

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
    setCapacity(parseInt(event.target.value));
  }

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

  const handleSubmit = (event) => {
    event.preventDefault();

    const locList = location.split(",");

    if (locList.length !== 4 || locList[2].trim().length !== 2) {
      setErrors(["Location format: [address, city, state_abbreviation, zipcode]"]);
    } else {
      const newEvent = {
        eventName,
        fullAddress : location,
        startDate,
        endDate,
        capacity,
        category : `${category === "Other" ? newCategory : category}`,
        culture : `${culture === "Other" ? newCulture : culture}`,
        username: authManager.user,
        adminApprove: authManager.hasRole('admin')
      };
  
      const init = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${localStorage.getItem('jwt_token')}`
        },
        body: JSON.stringify(newEvent)
      };
  
      fetch(`${window.API_URL}/event`, init)
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
              return Promise.reject('Something went wrong on the Event server.');
          }
        })
        .then(json => {
          if (json.eventId) {
            history.push('/event');
          } else {
            setErrors(json);
          }
        })
        .catch(err => console.error(err));
    }
    

  }
  return (
    <>
      <h2 className="mt-5">Add Event</h2>
      <form>
        <div className="form-group">
        <label htmlFor="eventName">Event Name:</label><br />
          <input className="form-control col-md-3 mb-3" type="text" id="eventName" name="eventName" placeholder="Event Name" value={eventName} onChange={handleEventName} required></input>
        </div>
{/* <div className="form-group">
          <label htmlFor="locationId">Location ID:</label><br />
          <input className="form-control col-md-3 mb-3" type="number" id="locationId" name="locationId" value={locationId} onChange={handleLocationId} ></input>
        </div> */}
        <div className="form-group">
          <label htmlFor="location">Location Address:</label><br />
          <input className="form-control col-md-3 mb-3" type="text" id="location" name="location" placeholder="Location Address" value={location} onChange={handleLocation} ></input>
        </div>

        <div className="form-group">
          <label htmlFor="startDate">Start Date:</label><br />
          <input className="form-control col-md-3 mb-3" type="date" id="startDate" name="startDate" placeholder="Start Date" value={startDate} onChange={handleStartDate} required ></input>
        </div>
        <div className="form-group">
          <label htmlFor="endDate">End Date:</label><br />
          <input className="form-control col-md-2 mb-3" type="date" id="endDate" name="endDate" placeholder="End Date" value={endDate} onChange={handleEndDate} required></input>
        </div>
        <div className="form-group">
        <label htmlFor="capacity">Capacity:</label><br />
          <input className="form-control col-md-1 mb-3" type="number" id="capacity" name="capacity" placeholder="Capacity" value={capacity} onChange={handleCapacity} required></input>
        </div>

        <div className="form-group">

          <label htmlFor="category">Category:</label><br />
          <select className="form-control col-md-2 mb-3" name="category" placeholder="Category" id="category" onChange={handleCategory}>
            <option></option>
            {categories.map((cate, i) => (
              <option key={i}>{`${cate.categoryName}`}</option>
            ))}

            <option>Other</option>
          </select>

          {category === "Other" ? <input className="form-control col-md-2 mb-3" type="text" id="newCategory" name="newCategory" placeholder="New Category" value={newCategory} onChange={handleNewCategory} ></input> : null}
        </div>


        <div className="form-group">

          <label htmlFor="culture">Culture:</label><br />
          <select className="form-control col-md-2 mb-3" name="culture" placeholder="Culture" id="culture" onChange={handleCulture}>
            <option></option>
            {cultures.map((cult, i) => (
              <option key={i}>{`${cult.cultureName}`}</option>
            ))}

            <option>Other</option>
          </select>

          {culture === "Other" ? <input className="form-control col-md-2 mb-3" type="text" id="newCulture" name="newCulture" placeholder="New Culture" value={newCulture} onChange={handleNewCulture} ></input> : null}
        </div>
        
        <div className="form-group">
          <button className="btn btn-primary" type="submit" onClick={handleSubmit}>Add Event</button>
          &nbsp;
          <button className="btn btn-secondary" type="button" onClick={() => history.push('/event')}>Cancel</button>
        </div>
      </form>
      <Errors errors={errors} />
    </>
  )

}

export default AddEvent;