import React from "react";
import AdminNavBar from "../components/AdminNavBar";
import { useState } from "react";
import axios from "axios";
import AuthServices from "../services/AuthServices";
import TraderTable from "../components/TraderTable";

const divStyle = {
  marginTop: "70px",
  marginBottom: "10px",
};

function AdminTraderStatsPage() {
  const [traders, setTraders] = useState([]);
  const [email, setEmail] = useState("");

  const user = AuthServices.getCurrentUser();

  React.useEffect(() => {
    axios
      .get("http://localhost:8080/traders", {
        headers: { Authorization: "Bearer " + user.accessToken },
      })
      .then((response) => {
        //setPost(response.data.data);
        console.log(response.data);
        setTraders(response.data);
      });
  }, []);

  async function getTradersByEmail(email) {
    axios
      .get("http://localhost:8080/traders/filter/" + email, {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + user.accessToken,
        },
      })
      .then(function (response) {
        setTraders(response.data);
        console.log(response.data);
      });
  }

  const textChangedEmail = (e) => {
    setEmail(e.target.value);
    if (e.target.value !== "") {
      getTradersByEmail(e.target.value);
    }
  };

  // const deleteTrader = (id) => {
  //   axios.delete("http://localhost:8080/users/" + id, {
  //     headers: { Authorization: "Bearer " + user.accessToken },
  //   });
  //   const newList = traders.filter((trader) => trader.id !== id);
  //   setTraders(newList);
  // };

  return (
    <div>
      <AdminNavBar />
      <div class="container">
        <div class="row justify-content-md-center">
          <div class="col-md-auto">
            <div class="input-group rounded" style={divStyle}>
              <input
                type="search"
                class="form-control rounded"
                placeholder="Search"
                aria-label="Search"
                aria-describedby="search-addon"
                onChange={textChangedEmail}
              />
            </div>
          </div>
          <TraderTable traders={traders} />
        </div>
      </div>
    </div>
  );
}

export default AdminTraderStatsPage;
