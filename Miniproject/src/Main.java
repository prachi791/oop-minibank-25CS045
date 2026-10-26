package Miniproject.src;


public class Main {

    public static void main(String[] args)
            throws CloneNotSupportedException {

        // Lab 3: Account object methods
        Account a1 = new Account("Riya", 4000);
        Account a2 = new Account(a1);

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

        // Lab 3: Customer and nested Address
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

        // Lab 2 Part B: Account transactions
        System.out.println("\n=== Lab 2 Part B: Account Transactions ===");

        Account[] accounts = {
                new Account("Priya", 2000),
                new Account("Karan", 1500),
                new Account("Neha")
        };

        accounts[0].deposit(1000);
        accounts[1].deposit(500);
        accounts[2].deposit(3000);

        accounts[0].withdraw(500);
        accounts[1].withdraw(250);

        for (Account account : accounts) {
            System.out.println(
                    account.getAccountNumber()
                    + " | " + account.getOwnerName()
                    + " | Balance: Rs. " + account.getBalance());
        }

        // Lab 4 Part B: Validator tests
        System.out.println("\n=== Lab 4: Validator Tests ===");

        System.out.println("\nMobile Validation:");
        System.out.println("9876543210 -> "
                + Validator.isValidMobile("9876543210"));
        System.out.println("12345 -> "
                + Validator.isValidMobile("12345"));

        System.out.println("\nEmail Validation:");
        System.out.println("riya@example.com -> "
                + Validator.isValidEmail("riya@example.com"));
        System.out.println("riya@com -> "
                + Validator.isValidEmail("riya@com"));

        System.out.println("\nPAN Validation:");
        System.out.println("ABCDE1234F -> "
                + Validator.isValidPan("ABCDE1234F"));
        System.out.println("ABC123 -> "
                + Validator.isValidPan("ABC123"));

        System.out.println("\nIFSC Validation:");
        System.out.println("SBIN0001234 -> "
                + Validator.isValidIfsc("SBIN0001234"));
        System.out.println("ABC123 -> "
                + Validator.isValidIfsc("ABC123"));

        // Lab 4: Command parsing
        System.out.println("\n=== Lab 4: Command Parsing ===");

        try {
            Command command =
                    CommandParser.parse("DEPOSIT AC0001 500");

            System.out.println("Type: " + command.type());
            System.out.println("Account Number: "
                    + command.accountNumber());
            System.out.println("Amount: " + command.amount());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Lab 4: Statement formatting
        System.out.println("\n=== Lab 4: Account Statement ===");
        System.out.println(StatementFormatter.buildStatement(a1));
    }
}
