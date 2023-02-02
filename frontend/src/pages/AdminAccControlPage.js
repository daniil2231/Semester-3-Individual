import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Badge from "react-bootstrap/Badge";
import React from "react";
import axios from "axios";
import AdminNavBar from "../components/AdminNavBar";

const divStyle = {
  marginTop: "100px",
  marginLeft: "350px",
  marginRight: "350px",
};

function AdminAccControlPage() {
  const [infoField, setInfoField] = React.useState({
    email: "",
    password: "",
  });

  const textChanged = (e) => {
    const value = e.target.value;
    setInfoField({
      ...infoField,
      [e.target.name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios.post(
      "http://localhost:8080/users",
      JSON.stringify({
        email: infoField.email,
        password: infoField.password,
      }),
      {
        headers: { "Content-Type": "application/json" },
      }
    );
  };

  return (
    <div>
      <AdminNavBar />
      <div>
        <Form
          style={divStyle}
          className="p-3 mb-2 bg-dark text-white"
          onSubmit={handleSubmit}
        >
          <h3>
            <Badge bg="secondary">Create admin account</Badge>
          </h3>

          <div className="form-group mt-3">
            <label>Email</label>
            <input
              type="text"
              name="email"
              data-cy="email"
              className="form-control mt-1"
              placeholder="example@mail.com"
              onChange={textChanged}
            />
          </div>

          <div className="form-group mt-3">
            <label>Password</label>
            <input
              type="password"
              name="password"
              data-cy="password"
              className="form-control mt-1"
              placeholder="*********"
              onChange={textChanged}
            />
          </div>

          <Form.Group as={Row} className="mb-3">
            <Col>
              <Button type="submit" data-cy="adminSubmit">
                Create
              </Button>
            </Col>
          </Form.Group>
        </Form>
      </div>
    </div>
  );
}

export default AdminAccControlPage;
