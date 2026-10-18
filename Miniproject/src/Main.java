
public class Main {
    public static void main(String[] args) {

        // Practical 3: Object methods demonstration
        Account a1 = new Account("Riya", 4000);
        Account a2 = new Account("Aman", 4000);

        System.out.println("=== Account Object Methods ===");
        System.out.println(a1);
        System.out.println("a1 equals a2 -> " + a1.equals(a2));
        System.out.println("a1 equals a1 -> " + a1.equals(a1));

        Object obj = a1;
        System.out.println(
            "obj instanceof Account -> " + (obj instanceof Account)
        );

        Customer c = new Customer(
            "Riya", "riya@example.com", "9876543210"
        );
        c.setAddress(
            new Customer.Address("12 MG Road", "Nadiad", "387001")
        );
        System.out.println(
            c + " lives in " + c.getAddress().getCity()
        );

        // Lab 2 Part B: Account array and transactions
        System.out.println("\n=== Lab 2 Part B: Account Transactions ===");

        Account[] accounts = {
            new Account("Priya", 2000),
            new Account("Karan", 1500),
            new Account("Neha")
        };

        // Deposits
        accounts[0].deposit(1000);
        accounts[1].deposit(500);
        accounts[2].deposit(3000);

        // Withdrawals
        accounts[0].withdraw(500);
        accounts[1].withdraw(250);

        // Display account details and balances
        for (Account account : accounts) {
            System.out.println(
                account.getAccountNumber()
                + " | " + account.getOwnerName()
                + " | Balance: Rs. " + account.getBalance()
            );
        }
    }
}
