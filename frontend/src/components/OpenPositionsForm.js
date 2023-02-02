import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import React, { useState } from "react";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import { ButtonGroup } from "react-bootstrap";
import axios from "axios";
import AddFundsModal from "./AddFundsModal";
import AuthServices from "../services/AuthServices";

const divStyle = {
  marginTop: "100px",
  marginRight: "100px",
  marginLeft: "100px",
  marginBottom: "100px",
};

function OpenPositionsForm(props) {
  const [value, setValue] = useState(0);
  const [type, setType] = useState("");
  const [modalShow, setModalShow] = React.useState(false);
  const [funds, setFunds] = useState(0);
  const [error, setError] = useState("");

  React.useEffect(() => {
    axios
      .get("http://localhost:8080/traders/" + AuthServices.getUserId(), {
        headers: {
          Authorization: "Bearer " + AuthServices.getCurrentUser().accessToken,
        },
      })
      .then((response) => {
        //setPost(response.data.data);
        console.log(response.data.funds);
        setFunds(response.data.funds);
      });
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    const traderId = AuthServices.getUserId();
    console.log(traderId);

    if (value > 0 && value < funds) {
      axios
        .post(
          "http://localhost:8080/positions",
          JSON.stringify({
            val: value,
            positionType: type,
            trader: {
              id: traderId,
            },
          }),
          {
            headers: {
              "Content-Type": "application/json",
              Authorization:
                "Bearer " + AuthServices.getCurrentUser().accessToken,
            },
          }
        )
        .then((response) => {
          console.log(response.data);
        });
    } else {
      setError("Invalid position size input.");
    }
  };

  const textChanged = (e) => {
    setValue(e.target.value);
  };

  return (
    <div style={divStyle}>
      <Form onSubmit={handleSubmit}>
        <Row>
          <Col>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label className="text-light">Position size</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter position size"
                onChange={textChanged}
                data-cy="submit"
              />
              <div onClick={() => setModalShow(true)}>
                <Form.Text className="text-light">
                  Account balance (Press to add funds): $
                  {Math.round((funds + Number.EPSILON) * 100) / 100}
                </Form.Text>
              </div>
            </Form.Group>
          </Col>
        </Row>
        <Row>
          <ButtonGroup className="d-flex">
            <Button
              variant="success"
              type="submit"
              onClick={() => setType("long")}
            >
              Long
            </Button>
            <Button
              variant="danger"
              type="submit"
              onClick={() => setType("short")}
            >
              Short
            </Button>
          </ButtonGroup>
        </Row>
      </Form>
      <div class="text-light">{error}</div>
      <AddFundsModal show={modalShow} onHide={() => setModalShow(false)} />
    </div>
  );
}

export default OpenPositionsForm;
