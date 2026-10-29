# MiniBank

MiniBank is a Java 17+ console banking project. The source is arranged in `model`, `util`, `service`, `exception`, and `minibank` packages. It includes account validation and transactions, account types, a reflective generic repository, concurrent batch deposits, file persistence, transaction logs, and an end-of-day report.

## Build and run

With Maven installed, from this folder run:

```text
mvn test
mvn package
java -jar target/minibank-1.0.jar
```

The JUnit 5 tests cover deposits, a withdrawal beyond the available balance, and concurrent deposits. Maven downloads JUnit the first time it runs the tests.

To compile and run without Maven:

```text
javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object FullName)
jar --create --file MiniBank.jar --main-class minibank.MiniBank -C out .
java -jar MiniBank.jar
```

The menu supports opening accounts, deposits, withdrawals, transfers, listing accounts, statements, reports, and batch deposits. Account data is stored in `data/accounts.dat`, transaction entries in `data/logs/transactions.log`, and the report in `data/report.txt`. These runtime files are created when the application is used.

## Practical-list alignment

The uploaded project had source and compiled class files through Practical 6, used the package `Miniproject.src`, and did not contain a build file, README, JAR manifest, persistence layer, collections repository, or service layer. The final project reorganizes the source into the package structure requested by Practical 6 and implements the Part B requirements for Practicals 1–14. Existing Java class names are retained; new classes use descriptive names from the practical list.

Creating the private GitHub repository named for a roll number and inviting faculty require the student's GitHub account and roll number, so those account actions are not represented in this ZIP. Maven is not installed in the available environment: the application sources and runnable JAR were built with Java 17-compatible tools, but the JUnit suite has not been run here.

The practical list has a few requirements that do not fit cleanly together as one final application. Practical 2 describes `Account` as concrete, while Practical 5 explicitly makes it abstract and introduces account subclasses; the final application follows the later abstract design. Practical 8 changes insufficient withdrawals from a `false` result to checked exceptions, so the integrated version follows Practical 8. Practical 1's five-action menu is retained and extended with list, statement, report, and batch actions required by later practicals. The original uploaded project used a `Main` class for examples; the runnable JAR starts at `minibank.MiniBank` so the menu is the application entry point.

Practical 10 asks students to demonstrate a deadlock and then fix it. `TransactionProcessor` contains both an opposite-order transfer demonstration method and an account-number ordered transfer method; the application uses the ordered method for safe transfers.
