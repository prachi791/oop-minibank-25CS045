# MiniBank – Java Banking System

## About the Project

MiniBank is a console-based banking application developed in Java as part of the Object-Oriented Programming (OOP) practicals. It provides a menu-driven interface for performing basic banking operations and demonstrates the use of Java programming concepts through a practical project.

The project is developed progressively across Practicals 1–14, with each practical adding new features and improving the application.

## Features

- **Account Management:** Open accounts and manage account details.
- **Multiple Account Types:** Supports Savings, Current, and Fixed Deposit accounts.
- **Banking Operations:** Deposit, withdraw, and transfer money.
- **Account Listing:** View available bank accounts.
- **Account Statements:** Display account-related information.
- **Input Validation:** Validate details such as mobile numbers, email addresses, PAN numbers, and IFSC codes.
- **Transaction Records:** Maintain transaction logs.
- **Batch Deposits:** Process deposits for multiple accounts.
- **Reports:** Generate an end-of-day report.
- **File Storage:** Store account information and transaction records locally.

## Technologies Used

- Java 17+
- Object-Oriented Programming
- Java Interfaces and Abstract Classes
- Inheritance and Polymorphism
- Collections and Generics
- Exception Handling
- Multithreading and Synchronization
- File Handling
- JUnit 5

## How to Run

### Prerequisites

- Java Development Kit (JDK) 17 or later
- Maven (optional, for building and running tests)

Check your Java installation:

```bash
java -version
```

### Run Using the Executable JAR

Open a terminal in the project directory and run:

```bash
java -jar MiniBank.jar
```

### Build and Run Using Maven

If Maven is installed, run:

```bash
mvn test
mvn package
java -jar target/minibank-1.0.jar
```

Maven builds the project and runs the available automated tests.

## Application Menu

The application provides options for:

1. Opening an account
2. Depositing money
3. Withdrawing money
4. Transferring money
5. Exiting the application

Additional options include listing accounts, viewing statements, generating reports, and processing batch deposits.

## Data Storage

MiniBank stores its data locally:

- **Account data:** `data/accounts.dat`
- **Transaction logs:** `data/logs/transactions.log`
- **End-of-day report:** `data/report.txt`

These files are created when the corresponding application features are used.

## Concepts Demonstrated

The project demonstrates important Java concepts, including:

- Classes, objects, and constructors
- Encapsulation, inheritance, and polymorphism
- Abstract classes and interfaces
- Lambda expressions and functional interfaces
- Generics and collections
- Exception handling
- File input and output
- Multithreading and thread safety
- Executable JAR creation

## Learning Outcomes

Through this project, I gained practical experience in designing a Java application, organizing classes, implementing banking operations, handling exceptions, storing data in files, and applying object-oriented programming concepts to a real-world-inspired problem.

## Conclusion

MiniBank brings together the Java concepts covered throughout the OOP practicals in one banking application. It demonstrates how a program can grow from basic account operations into a more structured application with multiple account types, transaction handling, data storage, and reporting.
