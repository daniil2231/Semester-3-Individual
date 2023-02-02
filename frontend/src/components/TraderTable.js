import Table from "react-bootstrap/Table";
import React from "react";
import Trader from "../components/Trader";
import Button from "react-bootstrap/Button";

function TraderTable(props) {
  return (
    <Table striped bordered hover variant="dark">
      <thead>
        <tr>
          <th>Email</th>
          <th>Traded volume</th>
          <th>Realized PnL</th>
          <th>Funds</th>
          {/* <th>Ban trader</th> */}
        </tr>
      </thead>
      <tbody>
        {props.traders.map((trader) => (
          <tr key={trader.id}>
            <td>
              <Trader
                key={trader.id}
                trader={trader.email}
                data-cy="traderEmail"
              />
            </td>
            <td>
              <Trader key={trader.id} trader={trader.tradedVolume} />
            </td>
            <td>
              <Trader key={trader.id} trader={trader.realizedPnl} />
            </td>
            <td>
              <Trader key={trader.id} trader={trader.funds} />
            </td>
            {/* <td>
              <Button
                className="btn bg-transparent"
                onClick={() => props.deleteTrader(trader.id)}
              >
                X
              </Button>
            </td> */}
          </tr>
        ))}
      </tbody>
    </Table>
  );
}

export default TraderTable;
