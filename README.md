#  Eagle Bank API

A banking REST API that allows users to register, manage bank accounts, and perform deposit or withdrawal transactions. 
Built with Spring Boot, Java 17, H2 Database, and Gradle 8.

---

Features

- Register new clients
- View, update, and delete client information
- Open, view, update, and delete bank accounts
- Deposit and withdraw money
- Track immutable transaction history

---

Tech Stack

- Java 17
- Spring Boot 3+
- Spring Data JPA
- H2 in-memory database
- Gradle 8
- Swagger / Springdoc OpenAPI

---

### Project Structure

```
src/main/java/com/eaglebank/
├── controller      // REST Controllers
├── pojo            // Request/Response DTOs
├── model          // JPA Entity Classes
├── repository     // Spring Data Repositories
├── service        // Interfaces
└── service/impl   // Service Implementations
```

---
### Prerequisites

- Java 17+
- Gradle 8+
- IDE (IntelliJ, Eclipse, etc.)

###  Clone the repo

```bash
git clone https://github.com/your-username/eagle-bank-api.git
cd eagle-bank-api
```

###  Run the application

```bash
./gradlew bootRun
```

---

##  API Documentation

Swagger UI will be available at:

```
http://localhost:8080/swagger-ui/index.html
```

---

##  Sample Endpoints

###  Clients

- `POST /clients` – create a client
- `GET /clients/{id}` – fetch client details
- `PUT /clients/{id}` – update client
- `DELETE /clients/{id}` – delete client

###  Bank Accounts

- `POST /accounts` – open new account
- `GET /accounts/{accountNumber}` – get account
- `PUT /accounts/{accountNumber}` – update type
- `DELETE /accounts/{accountNumber}` – delete account

###  Transactions

- `POST /accounts/{accountNumber}/transactions` – deposit or withdraw
- `GET /accounts/{accountNumber}/transactions` – list all
- `GET /accounts/{accountNumber}/transactions/{id}` – get specific transaction

---

##  H2 Database Console

H2 in-memory database is available at:

```
http://localhost:8080/h2-console
```

- JDBC URL: `jdbc:h2:mem:eagledb`
- Username: `sa`
- Password: (leave blank)

---

##  Sample Request (Deposit)

`POST /accounts/1/transactions`

```json
{
  "type": "DEPOSIT",
  "amount": 500.00
}
```

---

##  Notes

- All transaction records are immutable.
- Withdrawals will fail if the account has insufficient funds.
- Use `Long` IDs for accounts and clients.
- `accountNumber` is now a generated primary key.