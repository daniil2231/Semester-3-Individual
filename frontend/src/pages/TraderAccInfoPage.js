import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Badge from "react-bootstrap/Badge";
import TraderNavBar from "../components/TraderNavBar";
import React from "react";
import AuthServices from "../services/AuthServices";
import axios from "axios";

const divStyle = {
  marginTop: "100px",
  marginLeft: "350px",
  marginRight: "350px",
};

function TraderAccInfoPage() {
  const [infoField, setInfoField] = React.useState({
    nameOnCard: "",
    cardNumber: "",
    cardCvv: "",
    cardValidThru: "",
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

    const traderId = AuthServices.getUserId();

    axios
      .put(
        "http://localhost:8080/traders/update",
        JSON.stringify({
          id: traderId,
          nameOnCard: infoField.nameOnCard,
          cardNumber: infoField.cardNumber,
          cardCVV: infoField.cardCvv,
          cardValidThru: infoField.cardValidThru,
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
        //setPost(response.data.data);
        console.log(response.data);
      });
  };

  return (
    <div>
      <TraderNavBar />
      <div>
        <Form
          style={divStyle}
          className="p-3 mb-2 bg-dark text-white"
          onSubmit={handleSubmit}
        >
          <h3>
            <Badge bg="secondary">Payment information</Badge>
          </h3>

          <div className="form-group mt-3">
            <label>Name on card</label>
            <input
              type="text"
              name="nameOnCard"
              data-cy="nameOnCard"
              className="form-control mt-1"
              placeholder="Firstname Lastname"
              onChange={textChanged}
            />
          </div>

          <div className="form-group mt-3">
            <label>Card number</label>
            <input
              type="text"
              name="cardNumber"
              data-cy="cardNumber"
              className="form-control mt-1"
              placeholder="XXXX-XXXX-XXXX-XXXX"
              onChange={textChanged}
            />
          </div>

          <div className="form-group mt-3">
            <label>Card CVV</label>
            <input
              type="text"
              name="cardCvv"
              data-cy="cardCvv"
              className="form-control mt-1"
              placeholder="XXX"
              onChange={textChanged}
            />
          </div>

          <div className="form-group mt-3">
            <label>Card valid thru</label>
            <input
              type="text"
              name="cardValidThru"
              data-cy="cardValidThru"
              className="form-control mt-1"
              placeholder="MM/YY"
              onChange={textChanged}
            />
          </div>

          <Form.Group as={Row} className="mb-3">
            <Col>
              <Button type="submit" data-cy="submitEdit">
                Edit
              </Button>
            </Col>
          </Form.Group>
        </Form>
      </div>
    </div>
  );
}

export default TraderAccInfoPage;
