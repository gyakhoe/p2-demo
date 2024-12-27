import axios from "axios";
import React, { useState } from "react";
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

interface User {
    username: String,
    password: String,
    teamId: Number,
}

function Register() {




    //Define a state object to store the new user info
    const [newUser, setNewUser] = useState<User>({
        username: "",
        password: "",
        teamId: 0,
    });

    // For the navigation
    const navigate = useNavigate();


    function storeValues(event: React.ChangeEvent<HTMLInputElement>) {

        // I am going to store name and values in variables, for ease of use
        const name = event.target.name;
        const value = event.target.value;


        // Take whatever input was changed and set the matching state object field to the value of that input
        setNewUser((newUser) => ({ ...newUser, [name]: value }));
        console.log(newUser);
    }

    // Register functions that sends the username/password to the backend in a POST request
    async function register() {

        // TODO validate username and password

        try {
            const response = await axios.post("http://localhost:4444/users", newUser);
            console.log(response.data);
            alert("User registered " + newUser.username + response);
            navigate("/");
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <Container>

            <Row className="justify-content-center">
                <Col md={8}>
                    <Card>
                        <Card.Body>
                            <h3> Register </h3>

                            {/* Making a separate div for separate input box */}
                            <div>
                                <Form.Control
                                    type="text"
                                    placeholder="Enter username"
                                    name="username"
                                    className="w-50"
                                    onChange={storeValues}
                                />
                            </div>
                            <div>
                                <Form.Control
                                    type="password"
                                    placeholder="Enter password"
                                    name="password"
                                    className="w-50"
                                    onChange={storeValues}
                                />
                            </div>
                            <div>
                                <Form.Control
                                    type="number"
                                    placeholder="Enter team ID"
                                    name="teamId"
                                    className="w-50"
                                    onChange={storeValues}
                                />
                            </div>
                            <div >
                                <Button onClick={() => navigate("/")}>Back</Button>
                                <Button onClick={register}>Register</Button>
                            </div>
                        </Card.Body>
                    </Card>
                </Col>



            </Row>


        </Container >
    );
}



export default Register;