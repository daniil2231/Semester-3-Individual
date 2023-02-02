import { AdvancedChart } from "react-tradingview-embed";
import React from "react";

const divStyle = {
  marginLeft: "5px",
  marginTop: "5px",
};

function RealTimeChart() {
  return (
    <div style={divStyle}>
      <AdvancedChart widgetProps={{ theme: "dark" }} />
    </div>
  );
}

export default RealTimeChart;
