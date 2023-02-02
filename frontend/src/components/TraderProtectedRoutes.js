import { Navigate, Outlet } from "react-router-dom";
import AuthServices from "../services/AuthServices";

const TraderProtectedRoutes = () => {
  const user = AuthServices.getCurrentUser();
  console.log(user.role);

  if (user.role === "trader") {
    return <Outlet />;
  } else {
    return <Navigate to="/login" />;
  }
};

export default TraderProtectedRoutes;
