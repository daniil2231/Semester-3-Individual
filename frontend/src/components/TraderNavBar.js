import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import React from "react";

function TraderNavBar() {
  return (
    <>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand href="trade">BitBy</Navbar.Brand>
          <Nav className="container-fluid">
            <Nav.Link href="trade">Trade</Nav.Link>
            <Nav.Link href="tradestatistics">Statistics</Nav.Link>
            <Nav.Link href="accinfo">Account information</Nav.Link>
            <Nav.Link href="chat">Chat</Nav.Link>
            <Nav.Link href="/logout" className="border-left pl-2 ms-auto">
              Log out
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}

export default TraderNavBar;
