import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { Form } from "react-bootstrap";
import AuthServices from "../services/AuthServices";
import axios from "axios";
import { useState } from "react";
import React from "react";

function AddFundsModal(props) {
  const [value, setValue] = useState(0);
  const [error, setError] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (value > 0) {
      axios
        .put(
          "http://localhost:8080/traders",
          JSON.stringify({
            id: AuthServices.getUserId(),
            funds: value,
          }),
          {
            headers: { "Content-Type": "application/json" },
          }
        )
        .then((response) => {
          console.log(response.data);
        });
    } else {
      setError("Invalid fund amount.");
    }
  };

  const textChanged = (e) => {
    setValue(e.target.value);
  };

  return (
    <Modal
      {...props}
      size="sm"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">Add funds</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Control
              type="text"
              placeholder="$$$"
              autoFocus
              onChange={textChanged}
              data-cy="funds"
            />
          </Form.Group>
          <div>{error}</div>
          <Button type="submit" data-cy="addFunds">
            Add
          </Button>
        </Form>
      </Modal.Body>
    </Modal>
  );
}

export default AddFundsModal;
