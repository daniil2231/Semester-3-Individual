import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import React from "react";

function AdminNavBar() {
  return (
    <>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand href="adminhome">BitBy</Navbar.Brand>
          <Nav className="container-fluid">
            <Nav.Link href="adminhome">Home</Nav.Link>
            <Nav.Link href="admintraderstats">Trader statistics</Nav.Link>
            <Nav.Link href="adminacccontrol">Admin account control</Nav.Link>
            <Nav.Link href="/logout" className="border-left pl-2 ms-auto">
              Log out
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}

export default AdminNavBar;
