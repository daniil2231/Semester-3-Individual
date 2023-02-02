import Card from "react-bootstrap/Card";
import AdminNavBar from "../components/AdminNavBar";
import React from "react";
import axios from "axios";
import AuthServices from "../services/AuthServices";
import { useState } from "react";

const divStyle = {
  marginTop: "70px",
  marginLeft: "200px",
  marginRight: "200px",
};

function AdminHomePage() {
  const [volume, setVolume] = useState(0);
  const [price, setPrice] = useState(0);

  const user = AuthServices.getCurrentUser();

  React.useEffect(() => {
    axios
      .get("http://localhost:8080/traders/volume", {
        headers: { Authorization: "Bearer " + user.accessToken },
      })
      .then((response) => {
        console.log(response);
        setVolume(response.data.totalTradedVolume);
      });
  });

  React.useEffect(() => {
    axios.get("http://localhost:8080/tickers/prices").then((response) => {
      console.log(response);
      setPrice(response.data.price);
    });
  });

  return (
    <div>
      <AdminNavBar />
      <Card
        className="text-center p-3 mb-2 bg-dark text-white"
        style={divStyle}
      >
        <Card.Header>BTC Price</Card.Header>
        <Card.Body>
          <Card.Title>${price}</Card.Title>
        </Card.Body>
      </Card>
      <Card
        className="text-center p-3 mb-2 bg-dark text-white"
        style={divStyle}
      >
        <Card.Header>Overall traded volume</Card.Header>
        <Card.Body>
          <Card.Title>${volume}</Card.Title>
        </Card.Body>
      </Card>
    </div>
  );
}

export default AdminHomePage;
