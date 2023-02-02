import Card from "react-bootstrap/Card";
import TraderNavBar from "../components/TraderNavBar";
import React from "react";
import { useState } from "react";
import axios from "axios";
import AuthServices from "../services/AuthServices";

const divStyle = {
  marginTop: "70px",
  marginLeft: "200px",
  marginRight: "200px",
};

function TraderStatisticsPage() {
  const [volume, setVolume] = useState(0);
  const [realizedPnl, setRealizedPnl] = useState(0);

  const user = AuthServices.getCurrentUser();

  React.useEffect(() => {
    axios
      .get("http://localhost:8080/traders/" + AuthServices.getUserId(), {
        headers: { Authorization: "Bearer " + user.accessToken },
      })
      .then((response) => {
        //setPost(response.data.data);
        console.log(response);
        setVolume(response.data.tradedVolume);
        setRealizedPnl(response.data.realizedPnl);
      });
  });

  return (
    <div>
      <TraderNavBar />
      <div>
        <Card
          className="text-center p-3 mb-2 bg-dark text-white"
          style={divStyle}
        >
          <Card.Header>Total volume traded</Card.Header>
          <Card.Body>
            <Card.Title>${volume}</Card.Title>
          </Card.Body>
        </Card>
        <Card
          className="text-center p-3 mb-2 bg-dark text-white"
          style={divStyle}
        >
          <Card.Header>Total realized PnL</Card.Header>
          <Card.Body>
            <Card.Title>${realizedPnl}</Card.Title>
          </Card.Body>
        </Card>
      </div>
    </div>
  );
}

export default TraderStatisticsPage;
