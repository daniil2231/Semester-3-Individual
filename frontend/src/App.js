import "./App.css";
import React from "react";
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import TradePage from "./pages/TradePage";
import TraderStatisticsPage from "./pages/TraderStatisticsPage";
import TraderAccInfoPage from "./pages/TraderAccInfoPage";
import Auth from "./pages/Auth";
import ChatPage from "./pages/ChatPage";
import { Navigate } from "react-router-dom";
import AdminHomePage from "./pages/AdminHomePage";
import AdminAccControlPage from "./pages/AdminAccControlPage";
import AdminTraderStatsPage from "./pages/AdminTraderStatsPage";
import TraderProtectedRoutes from "./components/TraderProtectedRoutes";
import AdminProtectedRoutes from "./components/AdminProtectedRoutes";
import Logout from "./pages/Logout";

export default function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/" element={<Navigate to="/login" />} />
          <Route path="/login" element={<Auth />} />
          <Route path="/logout" element={<Logout />} />
          <Route element={<TraderProtectedRoutes />}>
            <Route path="/tradestatistics" element={<TraderStatisticsPage />} />
            <Route path="/trade" element={<TradePage />} />
            <Route path="/accinfo" element={<TraderAccInfoPage />} />
            <Route path="/chat" element={<ChatPage />} />
          </Route>
          <Route element={<AdminProtectedRoutes />}>
            <Route path="/adminhome" element={<AdminHomePage />} />
            <Route
              path="/admintraderstats"
              element={<AdminTraderStatsPage />}
            />
            <Route path="/adminacccontrol" element={<AdminAccControlPage />} />
          </Route>
        </Routes>
      </Router>
    </div>
  );
}
