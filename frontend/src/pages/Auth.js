import React from "react";
import { useState } from "react";
import axios from "axios";
import AuthServices from "../services/AuthServices";
import { useNavigate } from "react-router-dom";

function Auth() {
  let [authMode, setAuthMode] = useState("signin");
  const [message, setMessage] = useState("");
  const [registration, setRegistration] = React.useState({
    email: "",
    password: "",
    nameOnCard: "",
    cardNumber: "",
    cardCvv: "",
    cardValidThru: "",
  });

  const [user, setUser] = useState({
    email: "",
    password: "",
  });

  const { email, password } = user;

  let navigate = useNavigate();

  const textChanged = (e) => {
    const value = e.target.value;
    setRegistration({
      ...registration,
      [e.target.name]: value,
    });
  };

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios.post(
      "http://localhost:8080/traders",
      JSON.stringify({
        email: registration.email,
        password: registration.password,
        nameOnCard: registration.nameOnCard,
        cardNumber: registration.cardNumber,
        cardCVV: registration.cardCvv,
        cardValidThru: registration.cardValidThru,
      }),
      {
        headers: { "Content-Type": "application/json" },
      }
    );
  };

  const onSubmit = (e) => {
    e.preventDefault();

    console.log(user);
    AuthServices.login(user)
      .then((response) => {
        console.log(response);
        const accessToken = response.accessToken;
        const role = response.role;
        //for debug
        console.log("Login: " + "AccessToken: " + accessToken);
        console.log("Roles: " + role);
        if (role === "trader") {
          navigate("/trade");
        } else if (role === "admin") {
          navigate("/adminhome");
        }
      })
      .catch((message) => setMessage("Invalid credentials"));
  };

  const changeAuthMode = () => {
    setAuthMode(authMode === "signin" ? "signup" : "signin");
  };

  if (authMode === "signin") {
    return (
      <div className="Auth-form-container">
        <form className="Auth-form" onSubmit={onSubmit}>
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Welcome, BitByer</h3>
            <div className="form-group mt-3">
              <label>Email address</label>
              <input
                id="email"
                name="email"
                type="email"
                className="form-control mt-1"
                placeholder="Enter email"
                onChange={onInputChange}
                value={email}
              />
            </div>
            <div className="form-group mt-3">
              <label>Password</label>
              <input
                id="password"
                name="password"
                type="password"
                className="form-control mt-1"
                placeholder="Enter password"
                onChange={onInputChange}
                value={password}
              />
            </div>
            <div className="text-center">
              <label>New to ByBit?</label>{" "}
              <span className="link-primary" onClick={changeAuthMode}>
                Sign up
              </span>
            </div>
            <div className="d-grid gap-2 mt-3">
              <button type="submit" className="btn btn-primary">
                Login
              </button>
            </div>
            <div>{message}</div>
          </div>
        </form>
      </div>
    );
  }

  return (
    <div className="Auth-form-container">
      <form className="Auth-form" onSubmit={handleSubmit}>
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Become a BitByer</h3>
          <div className="form-group mt-3">
            <label>Email address</label>
            <input
              type="email"
              name="email"
              className="form-control mt-1"
              placeholder="Email Address"
              onChange={textChanged}
            />
          </div>
          <div className="form-group mt-3">
            <label>Password</label>
            <input
              type="password"
              name="password"
              className="form-control mt-1"
              placeholder="Password"
              onChange={textChanged}
            />
          </div>
          <div className="form-group mt-3">
            <label>Name on card</label>
            <input
              type="text"
              name="nameOnCard"
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
              className="form-control mt-1"
              placeholder="MM/YY"
              onChange={textChanged}
            />
          </div>
          <div className="text-center">
            <label>Already a BitByer?</label>{" "}
            <span className="link-primary" onClick={changeAuthMode}>
              Sign In
            </span>
          </div>
          <div className="d-grid gap-2 mt-3">
            <button type="submit" className="btn btn-primary">
              Register
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default Auth;
