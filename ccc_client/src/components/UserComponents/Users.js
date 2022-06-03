import { useContext, useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import AuthContext from "../../AuthContext";
import AddUser from "./AddUser";
import DeleteUser from "./DeleteUser";

const VIEW = {
  LIST: 'list',
  ADD: 'add',
  EDIT: 'edit',
  DELETE: 'delete'
};

function Users() {
  const [users, setUsers] = useState([]);
  const [user, setUser] = useState({});
  const [view, setView] = useState(VIEW.LIST);
  const history = useHistory();
  const authManager = useContext(AuthContext);

  const init = {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${localStorage.getItem('jwt_token')}`
    }
  }

  const getUsers = () => {
    return fetch(`${window.API_URL}/user`, init)
      .then(response => {
        if (response.status === 200) {
          return response.json()
        }
        return Promise.reject('Sorry! Something went wrong on the user server.');
      })
      .then(body => {
        setUsers(body);
      })
      .catch(err => console.error(err));
  }

  useEffect(() => {
    getUsers();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleAdd = (newUser) => {
    setUsers([...users, newUser]);
    setView(VIEW.LIST);
  }

  const handleAddSelect = () => {
    history.push('/user/add');
  }

  const handleDelete = (userId) => {
    const filteredList = [...users].filter(td => td.userId !== userId);
    setUsers(filteredList);
    setUser({});
    setView(VIEW.LIST);
  }

  const handleEditSelect = (user) => {
    history.push(`/user/edit/${user.userId}`);
  }

  const handleDeleteSelect = (user) => {
    history.push(`/user/delete/${user.userId}`);
  }

  const handleCancel = () => {
    setUser({});
    setView(VIEW.LIST);
  }

  return (
    <>
      <h2 className="mt-5">User List</h2>
      {authManager.user ? <button className="btn btn-success mb-3 mt-4" type="button" onClick={handleAddSelect}>Add User</button> : null}

      <table className="table table-sm">
        <thead>
          <tr>
            <th scope="col"></th>
            {/* <th scope="col">User ID</th> */}
            <th scope="col">First</th>
            <th scope="col">Middle</th>
            <th scope="col">Last</th>
            <th scope="col">Address</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            <th scope="col">Postal Code</th>
            <th scope="col">Phone</th>
            <th scope="col">Email</th>
          </tr>
        </thead>
        <tbody>
          {users.map((td, i) => (
            <tr key={td.userId}>
              <td>
                {authManager.user ? (<>
                  <button className="btn btn-info btn-sm" type="button" onClick={() => handleEditSelect(td)} >Edit</button>
                  &nbsp;

                  {authManager.hasRole('admin') ? <button className="btn btn-danger btn-sm" type="button" onClick={() => handleDeleteSelect(td)} >Delete</button> : null}
                </>) : null}
              </td>
              {/* <td>
                &nbsp;
                {i + 1}
              </td> */}
              <td>
                {td.firstName}
              </td>
              <td>
                {td.middleName}
              </td>
              <td>
                {td.lastName}
              </td>
              <td>
                {td.userAddress}
              </td>
              <td>
                {td.userCity}
              </td>
              <td>
                {td.userState}
              </td>
              <td>
                {td.userZip}
              </td>

              <td>
                {td.userPhone}
              </td>
              <td></td>
              <td>
                {td.userEmail}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {view === VIEW.ADD ? <AddUser handleAdd={handleAdd} handleCancel={handleCancel} /> : null}
      {view === VIEW.DELETE ? <DeleteUser user={user} handleDelete={handleDelete} handleCancel={handleCancel} /> : null}
    </>
  )
}

export default Users;