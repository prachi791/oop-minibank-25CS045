
package Miniproject.src;

public class Main {

    public static void main(String[] args)
            throws CloneNotSupportedException {

        // Lab 3: Account object methods
        SavingsAccount a1 = new SavingsAccount("Riya", 4000, 500);

        SavingsAccount a2 = new SavingsAccount(a1);

        System.out.println("=== Account Object Methods ===");
        System.out.println(a1);
        System.out.println(a2);

        System.out.println("a1 equals a2 -> " + a1.equals(a2));
        System.out.println("a1 equals a1 -> " + a1.equals(a1));

        Object obj = a1;
        System.out.println(
                "obj instanceof Account -> " + (obj instanceof Account));

        System.out.println(
                "Same hashCode -> " + (a1.hashCode() == a2.hashCode()));

        // Lab 3: Customer and Address
        System.out.println("\n=== Customer and Address ===");

        Customer c = new Customer(
                "Riya", "riya@example.com", "9876543210");

        c.setAddress(
                new Customer.Address("12 MG Road", "Nadiad", "387001"));

        System.out.println("Customer: " + c);
        System.out.println("Address: " + c.getAddress().getLine());
        System.out.println("City: " + c.getAddress().getCity());
        System.out.println("Pincode: " + c.getAddress().getPincode());

        // Lab 3: Customer cloning
        Customer clonedCustomer = c.clone();

        System.out.println("\n=== Customer Cloning ===");
        System.out.println("Original: " + c);
        System.out.println("Cloned: " + clonedCustomer);
        System.out.println("Same object -> " + (c == clonedCustomer));

        // Lab 2: Account transactions
        System.out.println("\n=== Lab 2: Account Transactions ===");

        Account[] transactionAccounts = {
                new SavingsAccount("Priya", 2000, 500),
                new CurrentAccount("Karan", 1500, 1000),
                new SavingsAccount("Neha", 0, 0)
        };

        transactionAccounts[0].deposit(1000);
        transactionAccounts[1].deposit(500);
        transactionAccounts[2].deposit(3000);

        transactionAccounts[0].withdraw(500);
        transactionAccounts[1].withdraw(250);

        for (Account account : transactionAccounts) {
            System.out.println(
                    account.getAccountNumber()
                            + " | " + account.getOwnerName()
                            + " | Balance: Rs. " + account.getBalance());
        }

        // Lab 4: Validator tests
        System.out.println("\n=== Lab 4: Validator Tests ===");

        System.out.println("\nMobile:");
        System.out.println("9876543210 -> "
                + Validator.isValidMobile("9876543210"));
        System.out.println("12345 -> "
                + Validator.isValidMobile("12345"));

        System.out.println("\nEmail:");
        System.out.println("riya@example.com -> "
                + Validator.isValidEmail("riya@example.com"));
        System.out.println("riya@com -> "
                + Validator.isValidEmail("riya@com"));

        System.out.println("\nPAN:");
        System.out.println("ABCDE1234F -> "
                + Validator.isValidPan("ABCDE1234F"));
        System.out.println("ABC123 -> "
                + Validator.isValidPan("ABC123"));

        System.out.println("\nIFSC:");
        System.out.println("SBIN0001234 -> "
                + Validator.isValidIfsc("SBIN0001234"));
        System.out.println("ABC123 -> "
                + Validator.isValidIfsc("ABC123"));

        // Lab 4: Command parsing
        System.out.println("\n=== Lab 4: Command Parsing ===");

        Command command = CommandParser.parse("DEPOSIT AC0001 500");

        System.out.println("Type: " + command.type());
        System.out.println("Account Number: "
                + command.accountNumber());
        System.out.println("Amount: " + command.amount());

        // Lab 4: Statement formatting
        System.out.println("\n=== Lab 4: Account Statement ===");
        System.out.println(StatementFormatter.buildStatement(a1));

        // Lab 5 Part B: Runtime polymorphism
        System.out.println("\n=== Lab 5: Account Types ===");

        Account[] typedAccounts = {
                new SavingsAccount("Aarav", 10000, 2000),
                new CurrentAccount("Meera", 5000, 3000),
                new FixedDepositAccount("Kabir", 20000)
        };

        for (Account account : typedAccounts) {
            System.out.println(
                    account.getClass().getSimpleName()
                            + " | Account: " + account.getAccountNumber()
                            + " | Interest Rate: " + account.interestRate()
                            + "%");

            if (account instanceof SavingsAccount savings) {
                System.out.println(
                        "Minimum Balance: Rs. "
                                + savings.getMinBalance());
            }
        }

        // Test withdrawal rules
        System.out.println("\n=== Lab 5: Withdrawal Rules ===");

        System.out.println("Savings withdraw Rs. 8500: "
                + typedAccounts[0].withdraw(8500));

        System.out.println("Current withdraw Rs. 7000: "
                + typedAccounts[1].withdraw(7000));

        System.out.println("Fixed Deposit withdraw Rs. 1000: "
                + typedAccounts[2].withdraw(1000));

        for (Account account : typedAccounts) {
            System.out.println(
                    account.getClass().getSimpleName()
                            + " | Balance: Rs. " + account.getBalance());
        }

        // ================= LAB 6 - PART B =================

        System.out.println("\n========== LAB 6: MINIBANK ==========");
        System.out.println("Part B - Interfaces, Lambda Expressions and JAR");

        // Create withdrawal rule using an anonymous class
        WithdrawRule anonymousRule = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

        // Create withdrawal rule using a lambda expression
        WithdrawRule lambdaRule = (account, amount) -> account.canWithdraw(amount);

        // Demonstrate both rules
        System.out.println("\n--- Withdrawal Rule: Anonymous Class ---");
        System.out.println("Withdrawal allowed: "
                + anonymousRule.allow(a1, 1000));

        System.out.println("\n--- Withdrawal Rule: Lambda Expression ---");
        System.out.println("Withdrawal allowed: "
                + lambdaRule.allow(a1, 1000));

        // Demonstrate yearly interest
        System.out.println("\n--- Interest Bearing Interface ---");
        System.out.println("Account balance: Rs. " + a1.getBalance());
        System.out.println("Interest rate: " + a1.interestRate() + "%");
        System.out.println("Yearly interest: Rs. " + a1.yearlyInterest());

        System.out.println("\n========== END OF LAB 6 ==========");
    }
}
