import axios from "axios";
import { useState } from "react";
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

interface UserCredential {
    username: String,
    password: String,
}

function Login() {

    //Define a state object to store the new user info
    const [userCredential, setUserCredential] = useState<UserCredential>({
        username: "",
        password: "",
    });


    // TODO State stuff
    function storeValues(event: React.ChangeEvent<HTMLInputElement>) {
        // I am going to store name and values in variables, for ease of use
        const name = event.target.name;
        const value = event.target.value;


        // Take whatever input was changed and set the matching state object field to the value of that input
        setUserCredential((userCredential) => ({ ...userCredential, [name]: value }));
        console.log(userCredential);
    }


    // we can use the useNavigatte hook to navigate between componenet programatically
    // which means we don't have to manually hcange the URL to switch components

    const navigate = useNavigate();

    async function login() {

        // Navigate to /teams if the user is player, and /users if the user is a manager

        navigate("/users");
    }

    const loginTwo = () => {
        console.log(userCredential);
        // TODO make sure the username and password are present before proceeding 
        axios.post("http://localhost:4444/auth", userCredential, { withCredentials: true })
            .then((response) => {
                console.log(response.data);
                if (response.data.role = "manager") {
                    navigate('/users')
                } else {
                    navigate('/teams');
                }
            }).catch((error) => {
                console.log("Error while logging in : ", error);
                alert(error.response.data["message"])
            })
    }



    return (
        <Container>

            <Row className="justify-content-center">
                <Col md={8}>
                    <Card>

                        <Card.Body>
                            <h3> Login </h3>
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
                            <div className="d-flex gap-1">
                                <Button onClick={loginTwo}>Login</Button>
                                <Button onClick={() => navigate("/register")}>Register</Button>
                            </div>
                        </Card.Body>
                    </Card>
                </Col>



            </Row>


        </Container >

    );
}

export default Login;