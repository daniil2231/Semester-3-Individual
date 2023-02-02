import Table from "react-bootstrap/Table";
import React from "react";
import Position from "./Position";
import Button from "react-bootstrap/Button";

const divStyle = {
  marginTop: "10px",
};

function PositionTable(props) {
  return (
    <Table striped bordered hover variant="dark" style={divStyle}>
      <thead>
        <tr>
          <th>Type</th>
          <th>Value</th>
          <th>Entry price</th>
          <th>Liquidation price</th>
          <th>Unrealized PnL %</th>
          <th>Close position</th>
        </tr>
      </thead>
      <tbody>
        {props.positions.map((position) => (
          <tr key={position.id}>
            <td>
              <Position key={position.id} position={position.positionType} />
            </td>
            <td>
              <Position key={position.id} position={position.val} />
            </td>
            <td>
              <Position key={position.id} position={position.entryPrice} />
            </td>
            <td>
              <Position
                key={position.id}
                position={
                  Math.round(
                    (position.liquidationPrice + Number.EPSILON) * 100
                  ) / 100
                }
              />
            </td>
            <td>
              <Position
                key={position.id}
                position={
                  Math.round((position.changeInPrice + Number.EPSILON) * 100) /
                  100
                }
              />
            </td>
            <td>
              <Button
                className="btn bg-transparent"
                data-cy="close"
                onClick={() => props.closePosition(position.id)}
              >
                X
              </Button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
}

export default PositionTable;
