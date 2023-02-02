import React from "react";
import { Row } from "react-bootstrap";
import { Col } from "react-bootstrap";
import RealTimeChart from "../components/RealTimeChart";
import OpenPositionsForm from "../components/OpenPositionsForm";
import PositionTable from "../components/PositionTable";
import { useState } from "react";
import axios from "axios";
import AuthServices from "../services/AuthServices";
import TraderNavBar from "../components/TraderNavBar";

function TradePage() {
  //const [post, setPost] = React.useState(null);
  const [positions, setPositions] = useState([]);

  const user = AuthServices.getCurrentUser();

  React.useEffect(() => {
    axios
      .get("http://localhost:8080/positions/all/" + AuthServices.getUserId(), {
        headers: { Authorization: "Bearer " + user.accessToken },
      })
      .then((response) => {
        //setPost(response.data.data);
        console.log(response.data);
        setPositions(response.data);
      });
  }, []);

  const closePosition = (id) => {
    axios.delete("http://localhost:8080/positions/" + id, {
      headers: { Authorization: "Bearer " + user.accessToken },
    });
    const newList = positions.filter((position) => position.id !== id);
    setPositions(newList);
  };

  return (
    <div>
      <TraderNavBar />
      <div className="container-fluid">
        <Row>
          <Col xs={7}>
            <RealTimeChart />
          </Col>
          <Col className="bg-dark mt-1 me-3">
            <OpenPositionsForm />
          </Col>
        </Row>
        <Row>
          <Col className="mx-1">
            <PositionTable
              closePosition={closePosition}
              positions={positions}
            />
          </Col>
        </Row>
      </div>
    </div>
  );
}

export default TradePage;
