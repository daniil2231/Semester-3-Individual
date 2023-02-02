import React from "react";
import { Navigate } from "react-router-dom";

function Logout() {
  console.log(localStorage.getItem("user"));

  const noUser = {
    accessToken: "",
    role: "",
  };

  localStorage.setItem("user", JSON.stringify(noUser));
  console.log(localStorage.getItem("user"));

  return <Navigate to="/login" />;
}

export default Logout;
