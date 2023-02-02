import api from "./Http-common";
import jwtDecode from "jwt-decode";

const login = (user) => {
  return api.post(`/login`, user).then((response) => {
    if (response.data.accessToken) {
      localStorage.setItem("user", JSON.stringify(response.data));
    }
    return response.data;
  });
};

const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const getUserId = () => {
  var token = localStorage.getItem("user");
  if (token) {
    var user = jwtDecode(token);
    console.log(user);
  } else {
    user = {
      id: "Not logged in",
    };
  }
  return user.id;
};

const getUserRole = () => {
  var token = localStorage.getItem("user");
  if (token) {
    var user = jwtDecode(token);
    console.log(user);
  } else {
    user = {
      id: "Not logged in",
    };
  }
  return user.roles;
};

const AuthenticationService = {
  login,
  logout,
  getCurrentUser,
  getUserId,
  getUserRole,
};

export default AuthenticationService;
