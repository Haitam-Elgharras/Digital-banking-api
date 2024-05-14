# Digital Banking API

## Overview

Digital Banking API is a Spring Boot application designed to manage bank accounts, customers, and account operations. It provides comprehensive functionalities for handling current accounts, saving accounts, and various banking operations through a RESTful API.

## Functionalities

### Customer Management

- **Create Customer:** Add a new customer to the system.
  - Endpoint: `POST /customers`
  - Request Body: `CustomerDTO`
- **Retrieve Customers:** Get a list of all customers.
  - Endpoint: `GET /customers`
- **Retrieve Customer by ID:** Get details of a specific customer by their ID.
  - Endpoint: `GET /customers/{id}`
- **Update Customer:** Update the information of an existing customer.
  - Endpoint: `PUT /customers/{id}`
  - Request Body: `CustomerDTO`
- **Delete Customer:** Remove a customer from the system.
  - Endpoint: `DELETE /customers/{id}`
- **Search Customers:** Search for customers by keyword.
  - Endpoint: `GET /customers/searchCustomer`
  - Query Parameter: `keyword`

### Bank Account Management

- **Create Current Account:** Add a new current account with an overdraft limit.
  - Endpoint: `POST /bankAccounts/currentAccount`
  - Request Body: `CurrentAccountRequest`
- **Create Saving Account:** Add a new saving account with an interest rate.
  - Endpoint: `POST /bankAccounts/savingAccount`
  - Request Body: `SavingAccountRequest`
- **Retrieve Bank Accounts:** Get a list of all bank accounts.
  - Endpoint: `GET /bankAccounts`
- **Retrieve Bank Account by ID:** Get details of a specific bank account by its ID.
  - Endpoint: `GET /bankAccounts/{id}`
- **Get Accounts by Customer ID:** Get all accounts associated with a specific customer.
  - Endpoint: `GET /bankAccounts/customers/{customerId}`

### Account Operations

- **Get Account History:** Retrieve the operation history of a specific account.
  - Endpoint: `GET /bankAccounts/{accountId}/operations`
- **Get Account History with Pagination:** Retrieve paginated operation history of a specific account.
  - Endpoint: `GET /bankAccounts/{accountId}/pageOperation`
  - Query Parameters: `page`, `size`
- **Debit Account:** Perform a debit operation on an account.
  - Endpoint: `POST /bankAccounts/debit`
  - Request Body: `AccountOperationRequest`
- **Credit Account:** Perform a credit operation on an account.
  - Endpoint: `POST /bankAccounts/credit`
  - Request Body: `AccountOperationRequest`
- **Transfer Between Accounts:** Transfer funds between two bank accounts.
  - Endpoint: `POST /bankAccounts/transfer`
  - Request Body: `TransferDTO`

### Security

- **JWT Authentication:** The application uses JWT (JSON Web Token) for securing endpoints. Users must authenticate to access most of the API endpoints.
- **In-Memory User Details:** Two users (`user` and `admin`) are pre-configured for demonstration purposes.
- **CORS Configuration:** The application allows cross-origin requests from `http://localhost:4200` for frontend integration.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- An IDE such as IntelliJ IDEA or Eclipse

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/digital-banking-api.git
   ```
2. Navigate to the project directory:
   ```sh
   cd digital-banking-api
   ```
3. Build the project with Maven:
   ```sh
   mvn clean install
   ```

### Running the Application

To run the application, use the following command:
```sh
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## Sample Data Initialization

The application includes a `CommandLineRunner` bean that initializes sample data for testing purposes. This includes creating sample customers, bank accounts (current and saving), and account operations.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request for any feature addition or bug fix.
