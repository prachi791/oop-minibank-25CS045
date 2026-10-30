# Object-Oriented Programming in Java — Labs & MiniBank

This repository contains Java practicals for an Object-Oriented Programming course, followed by a larger **MiniBank** console application. The practicals cover core Java and object-oriented ideas, then progress into exceptions, threads, files, collections, and generics. The MiniBank project brings many of these ideas together in a multi-package banking application.

## Table of Contents

- [Repository Structure](#repository-structure)
- [Practicals](#practicals)
- [MiniBank Project](#minibank-project)
- [How to Compile and Run](#how-to-compile-and-run)
- [Prerequisites](#prerequisites)

## Repository Structure

    .
    ├── lab-01/ … lab-14/       # Practical exercises, grouped by lab and practical
    ├── Minibank/               # Maven-based MiniBank application
    │   ├── src/main/java/      # Application source (minibank, model, service, util, exception)
    │   ├── src/test/java/      # JUnit tests
    │   ├── data/               # Included account data
    │   └── pom.xml
    ├── .devcontainer/          # Java 17 development container configuration
    ├── manifest.txt
    └── README.md

Most lab exercises are in their own `lab-NN/Practical-N/` folder. Lab 10 groups its examples into `ProducerConsumer`, `ThreadPool`, and `Advanced Learner` folders. The first Lab 8 folder is named `Practicak-1` in the project as provided.

## Practicals

| Folder | Topic | Examples in the repository |
| --- | --- | --- |
| `lab-01` | Basic Java programming and control flow | Vending machine, toll booth, and Rock–Paper–Scissors–Lizard–Spock (`VendingMachine.java`, `TollBooth.java`, `RPSLS.java`) |
| `lab-02` | Conditionals and object/domain modeling | Thermostat, cinema seat booking, and parking lot (`Thermostat.java`, `CinemaShow.java`, `ParkingLot.java`) |
| `lab-03` | Classes, constructors, methods, and encapsulation | Point, card, and fraction equality and display (`Point.java`, `Card.java`, `Fraction.java`, with `Driver.java` examples) |
| `lab-04` | String manipulation and regular expressions | Password checks, chat filtering, and template filling (`PasswordChecker.java`, `ChatFilter.java`, `TemplateFiller.java`) |
| `lab-05` | Inheritance, abstract classes, and polymorphism | Shapes, employee salary types, and media late fees (`ShapeDemo.java`, `EmployeeSalary.java`, `Media.java`) |
| `lab-06` | Interfaces, marker interfaces, and lambdas | Device controls, urgent notifications, and discount rules (`RemoteControl.java`, `MarkerInterface.java`, `DiscountApp.java`) |
| `lab-07` | Annotations and reflection | Form validation and a small annotation-based test runner (`FormValidatorDemo.java`, `MiniTestRunner.java`) |
| `lab-08` | Exception handling and custom exceptions | Guarded division and warehouse stock/quantity validation (`GuardedCalculator.java`, `WarehouseDemo.java`) |
| `lab-09` | Multithreading and synchronization | Shared counter race conditions and concurrent seat booking (`CounterRaceDemo.java`, `SeatBookingDemo.java`) |
| `lab-10` | Producer-consumer, thread pools, and deadlocks | Bounded buffer, executor tasks, and deadlock/fix demonstrations (`Demo.java`, `DeadlockDemo.java`, `DeadlockFixed.java`) |
| `lab-11` | Serialization and file I/O | Object serialization and log-file analysis (`SerializationDemo.java`, `LogAnalyzer.java`) |
| `lab-12` | Java collections: maps and sets | Word-frequency counting and unique visitor tracking (`WordFrequency.java`, `UniqueVisitors.java`) |
| `lab-13` | Generics and bounded type parameters | Generic containers and numeric statistics (`GenericContainersDemo.java`, `BoundedStatsDemo.java`) |
| `lab-14` | Service layer and banking system | Account model, account service, and menu-driven bank app (`Account.java`, `AccountService.java`, `BankApp.java`) |

## MiniBank Project

`Minibank/` is a separate Java 17+ console application built with Maven. It includes account types and validation, deposits, withdrawals and transfers, transaction processing, persistence, reporting, and custom exceptions. Its source is organized into `minibank`, `model`, `service`, `util`, and `exception` packages; a JUnit 5 service test is under `src/test/java/`.

## How to Compile and Run

### Run a practical

Open a terminal in the practical's folder, compile its Java files, then run the class containing `main`. For example, in `lab-01/Practical-1`:

**Windows PowerShell**

    New-Item -ItemType Directory -Force out | Out-Null
    javac -d out (Get-ChildItem -Filter *.java | ForEach-Object FullName)
    java -cp out VendingMachine

**macOS / Linux**

    mkdir -p out
    javac -d out *.java
    java -cp out VendingMachine

For another practical, use its main class name instead (for example, `Thermostat`, `FormValidatorDemo`, or `BankApp`). Compile all `.java` files in the selected folder together when the example uses multiple source files. Lab 10's examples are in separate subfolders; compile and run each example from its own folder. Some practicals require interactive input.

### Build and run MiniBank

From the `Minibank/` directory, run:

    mvn test
    mvn package
    java -jar target/minibank-1.0.jar

The first Maven run may download the test dependency. The application stores account data under `Minibank/data/` and can create other runtime output files while in use.

## Prerequisites

- **JDK 17 or newer** (`.devcontainer/devcontainer.json` specifies Java 17).
- **Apache Maven** to build and test the MiniBank project.
- A terminal or Java IDE. Git is useful for version control but is not needed to run the programs.

## Author

- **Name:** Prachi Modi
- **ID:**   25CS045
