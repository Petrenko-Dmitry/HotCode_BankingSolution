## Getting Started
To set up the MySQL database, navigate to the project's root directory where the docker-compose.yml file is located.

Once the application is running, you can send HTTP requests to interact with the banking data using the request.http file is located in the root directory of the project.

## Tests
After running the tests using the command:

```mvn clean test```

You can view the test coverage statistics by navigating to the following link:
http://localhost:63342/HotCode_BankingSolution/target/site/jacoco/index.html, as per point 6 of the requirements.

## Technologies Used
- **Java 22**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **JUnit**
- **Mockito**

## Task
You are tasked with building a simple REST API for a banking application. The API should allow users to perform basic banking operations such as creating accounts,
making deposits, and transferring funds.

Requirements:
1. Account Management
Implement the following operations related to user accounts:
Create a new account with an initial balance.
Get account details by account number.
List all accounts.
2. Account Transactions
Implement the following operations related to account transactions:
Deposit funds into an account.
Withdraw funds from an account.
Transfer funds between two accounts.
3. REST Endpoints
Create RESTful endpoints to expose the functionality of both the Account Management and Account Transactions.
4. Authentication & Authorization
This is not required. Focus on what is asked.
5. Technology Stack
Language: Java
You can choose other technologies and frameworks as you see fit.
6. Quality
Your code should be covered with unit tests.
Used project build tool should be configured so that code coverage report can be generated.
