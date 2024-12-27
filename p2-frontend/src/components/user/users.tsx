import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Container, Table } from "react-bootstrap";

// TODO: we will make the user for type safety
interface User {
    userId: number,
    username: String,
    role: String,
    team: any,
}


function Users() {

    // state object to store the user array from the DB
    const [users, setUsers] = useState<User[]>([]);

    // TODO: useEffect to call the get request
    useEffect(() => { getAllUser() }, []);

    // Function to get all users;
    async function getAllUser() {
        try {
            const response = await axios.get("http://localhost:4444/users", { withCredentials: true });
            console.log(response.data);
            setUsers(response.data);
        } catch (error) {
            console.log("Error", error);
        }

    }

    return (
        <Container>
            <Table className="table-success table-hover">
                <thead className="table-dark">
                    <tr>
                        <td>User ID</td>
                        <td>Username</td>
                        <td>Role</td>
                        <td>Team Name</td>
                        <td>Options</td>
                    </tr>
                </thead>
                <tbody>

                    {
                        users.map((user) => (
                            <tr>
                                <td>{user.userId}</td>
                                <td>{user.username}</td>
                                <td>{user.role}</td>
                                <td>{user.team.teamName}</td>
                                <td>
                                    {/* Conditional render - show promote button if user is player or demote button if the user is manager */}
                                    {user.role === 'player'
                                        ? <Button>Promote</Button>
                                        : <Button className="btn-danger">Demote</Button>
                                    }
                                </td >
                            </tr >
                        ))
                    }
                </tbody>
            </Table>
        </Container>
    );

}

export default Users;