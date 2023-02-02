describe("BitBy e2e tests", () => {
  beforeEach(() => {
    // reset and seed the database prior to every test
    //cy.exec("npm run db:reset && npm run db:seed");
    // seed a user in the DB that we can control from our tests
    // assuming it generates a random password for us
    // cy.request("POST", "http://localhost:8080/traders", {
    //   email: "test9@mail.com",
    //   password: "123",
    //   cardNumber: "1212-1212-1212-1212",
    //   cardCVV: "123",
    //   nameOnCard: "dadner",
    //   cardValidThru: "10/24",
    // })
    //   .its("body")
    //   .as("currentUser");
  });

  it("trader login", function () {
    // destructuring assignment of the this.currentUser object
    //const { email, password } = this.currentUser;

    cy.visit("http://localhost:3000/login");

    // cy.request("POST", "http://localhost:8080/login", {
    //   email: "test@mail.com",
    //   password: "123",
    // }).then((response) => {
    //   expect(response.body).to.have.property("accessToken");
    //   cy.setLocalStorage("token", response.body.accessToken);
    // });

    cy.get("input[name=email]").type("test@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /trade
    cy.url().should("include", "/trade");

    // cypress doesnt take my button onclick into account and it the test fails because of that
    // cy.get("[data-cy='submit']").type(500);

    // more attempts to make opening a position work but they fail because cy.getLocalStorage("token") returns /Object object/
    // cy.contains("Short").click();

    // cy.request("POST", "http://localhost:8080/positions", {
    //   val: 500,
    //   positionType: "short",
    //   trader: {
    //     id: 20,
    //   },
    // }).then((response) => {
    //   expect(response.body).to.have.property("liquidationPrice");
    //   expect(response.body).to.have.property("entryPrice");
    // });

    // cy.request({
    //   method: "POST",
    //   url: "http://localhost:8080/positions",
    //   body: {
    //     val: 500,
    //     positionType: "short",
    //     trader: {
    //       id: 20,
    //     },
    //   },
    //   headers: {
    //     Authorization: "Bearer " + cy.getLocalStorage("token"),
    //   },
    // }).then((response) => {
    //   expect(response.body).to.have.property("liquidationPrice");
    //   expect(response.body).to.have.property("entryPrice");
    // });
  });

  it("trader logout", function () {
    cy.visit("http://localhost:3000/login");

    cy.get("input[name=email]").type("test@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /trade
    cy.url().should("include", "/trade");

    cy.contains("Log out").click();

    cy.url().should("include", "/login");
  });

  it("adding funds to account", function () {
    cy.visit("http://localhost:3000/login");

    cy.get("input[name=email]").type("test@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /trade
    cy.url().should("include", "/trade");

    cy.contains("Account balance").click();

    cy.get("[data-cy='funds']").type(500);

    cy.get('[data-cy="addFunds"]').click();
  });

  it("close position", function () {
    cy.visit("http://localhost:3000/login");

    cy.get("input[name=email]").type("test@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /trade
    cy.url().should("include", "/trade");

    cy.wait(2000);

    cy.get("[data-cy='close']").first().click();
  });

  it("edit payment information", function () {
    cy.visit("http://localhost:3000/login");

    cy.get("input[name=email]").type("test@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /trade
    cy.url().should("include", "/trade");

    cy.contains("Account information").click();

    cy.url().should("include", "/accinfo");

    cy.get("[data-cy='nameOnCard']").type("Random Name");

    cy.get("[data-cy='cardNumber']").type("1234-1234-1234-1234");

    cy.get("[data-cy='cardCvv']").type("444");

    cy.get("[data-cy='cardValidThru']").type("10/28");

    cy.get("[data-cy='submitEdit']").click();
  });

  // how do i test if i get the traded volume and pnl values?
  it("trader statistics", function () {
    cy.visit("http://localhost:3000/login");

    cy.get("input[name=email]").type("test@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /trade
    cy.url().should("include", "/trade");

    cy.contains("Statistics").click();

    cy.url().should("include", "/tradestatistics");
  });

  // how do i test if i get the btc price and traded volume values?
  it("admin login", function () {
    cy.visit("http://localhost:3000/login");

    cy.get("input[name=email]").type("admin@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /adminhome
    cy.url().should("include", "/adminhome");
  });

  it("admin logout", function () {
    cy.visit("http://localhost:3000/login");

    cy.get("input[name=email]").type("admin@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /adminhome
    cy.url().should("include", "/adminhome");

    cy.contains("Log out").click();

    cy.url().should("include", "/login");
  });

  it("admin view trader statistics", function () {
    cy.visit("http://localhost:3000/login");

    cy.get("input[name=email]").type("admin@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /adminhome
    cy.url().should("include", "/adminhome");

    cy.contains("Trader statistics").click();

    cy.url().should("include", "/admintraderstats");

    cy.wait(2000);

    cy.contains("@mail.com");
  });

  it("create admin account", function () {
    cy.visit("http://localhost:3000/login");

    cy.get("input[name=email]").type("admin@mail.com");

    cy.get("input[name=password]").type("123");

    cy.contains("Login").click();

    // we should be redirected to /adminhome
    cy.url().should("include", "/adminhome");

    cy.contains("Admin account control").click();

    cy.url().should("include", "/adminacccontrol");

    cy.get("[data-cy='email']").type("admin10@mail.com");

    cy.get("[data-cy='password']").type("1234");

    cy.get("[data-cy='adminSubmit']").click();
  });
});
