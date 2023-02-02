import { Navigate, Outlet } from "react-router-dom";
import AuthServices from "../services/AuthServices";

const AdminProtectedRoutes = () => {
  const user = AuthServices.getCurrentUser();
  console.log(user.role);

  if (user.role === "admin") {
    return <Outlet />;
  } else {
    return <Navigate to="/login" />;
  }
};

export default AdminProtectedRoutes;
