package minibank;

import exception.BankException;
import model.Account;
import model.BankInfo;
import minibank.MenuOption;
import service.BankService;
import service.Repository;
import service.TransactionProcessor;
import util.ReportGenerator;
import util.StatePersister;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class MiniBank {
    private static final Path DATA_FILE = Path.of("data", "accounts.dat");
    private static final Path LOG_FILE = Path.of("data", "logs", "transactions.log");
    private static final Path REPORT_FILE = Path.of("data", "report.txt");

    public static void main(String[] args) {
        Repository<Account> repository = new Repository<>();
        try {
            for (Account account : StatePersister.load(DATA_FILE)) repository.save(account);
        } catch (Exception e) {
            System.out.println("Could not load saved accounts: " + e.getMessage());
        }
        BankService bank = new BankService(repository, LOG_FILE);
        BankInfo info = new BankInfo("MiniBank", "Nadiad");
        System.out.println("========================================");
        System.out.println(info.name() + " - " + info.branch() + " Branch");
        System.out.println("========================================");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readChoice(scanner);
            MenuOption option = toOption(choice);
            if (option == null) {
                System.out.println("Invalid choice. Please enter a listed number.\n");
                continue;
            }
            try {
                String result = switch (option) {
                    case OPEN_ACCOUNT -> openAccount(scanner, bank);
                    case DEPOSIT -> deposit(scanner, bank);
                    case WITHDRAW -> withdraw(scanner, bank);
                    case TRANSFER -> transfer(scanner, bank);
                    case LIST_ACCOUNTS -> listAccounts(bank);
                    case STATEMENT -> bank.statement(readText(scanner, "Account number: "));
                    case REPORT -> ReportGenerator.generate(Path.of("data", "logs"), REPORT_FILE);
                    case BATCH -> runBatch(scanner, bank);
                    case EXIT -> "Thank you for using MiniBank. Goodbye!";
                };
                System.out.println(result + "\n");
                if (option == MenuOption.EXIT) running = false;
            } catch (BankException | java.io.IOException | InterruptedException e) {
                System.out.println("Unable to complete request: " + e.getMessage() + "\n");
                if (e instanceof InterruptedException) Thread.currentThread().interrupt();
            } catch (RuntimeException e) {
                System.out.println("Invalid input: " + e.getMessage() + "\n");
            }
        }
        try {
            ArrayList<Account> accounts = bank.listAccounts();
            StatePersister.save(accounts.toArray(new Account[0]), DATA_FILE);
            System.out.println("Accounts saved.");
        } catch (java.io.IOException e) {
            System.out.println("Could not save accounts: " + e.getMessage());
        }
        try (BankSession session = new BankSession()) {
            System.out.println("Goodbye from " + info.name() + ".");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

    private static String openAccount(Scanner scanner, BankService bank) throws BankException {
        String name = readText(scanner, "Customer name: ");
        String email = readText(scanner, "Email: ");
        String mobile = readText(scanner, "Mobile: ");
        long opening = readLong(scanner, "Opening balance: ");
        Account account = bank.openAccount(name, email, mobile, opening);
        return "Account opened: " + account;
    }
    private static String deposit(Scanner scanner, BankService bank) throws BankException {
        String id = readText(scanner, "Account number: ");
        long amount = readLong(scanner, "Amount: ");
        bank.deposit(id, amount);
        return "Deposit complete. " + bank.statement(id);
    }
    private static String withdraw(Scanner scanner, BankService bank) throws BankException {
        String id = readText(scanner, "Account number: ");
        long amount = readLong(scanner, "Amount: ");
        bank.withdraw(id, amount);
        return "Withdrawal complete. " + bank.statement(id);
    }
    private static String transfer(Scanner scanner, BankService bank) throws BankException {
        String from = readText(scanner, "From account: ");
        String to = readText(scanner, "To account: ");
        long amount = readLong(scanner, "Amount: ");
        bank.transfer(from, to, amount);
        return "Transfer complete.";
    }
    private static String listAccounts(BankService bank) {
        StringBuilder text = new StringBuilder("Accounts:");
        for (Account account : bank.listAccounts()) text.append('\n').append(account);
        return text.toString();
    }
    private static String runBatch(Scanner scanner, BankService bank) throws InterruptedException {
        TransactionProcessor processor = new TransactionProcessor();
        for (Account account : bank.listAccounts()) processor.addAccount(account);
        String id = readText(scanner, "Account number for batch deposits: ");
        long amount = readLong(scanner, "Amount per deposit: ");
        int count = (int) readLong(scanner, "Number of deposits: ");
        for (int i = 0; i < count; i++) processor.submitDeposit(id, amount);
        processor.stop();
        return "Batch complete. Current account: " + processor.getAccounts().get(id);
    }
    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    private static long readLong(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return Long.parseLong(scanner.nextLine().trim());
    }
    private static int readChoice(Scanner scanner) {
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (RuntimeException e) { return -1; }
    }
    private static MenuOption toOption(int choice) {
        return switch (choice) {
            case 1 -> MenuOption.OPEN_ACCOUNT;
            case 2 -> MenuOption.DEPOSIT;
            case 3 -> MenuOption.WITHDRAW;
            case 4 -> MenuOption.TRANSFER;
            case 5 -> MenuOption.EXIT;
            case 6 -> MenuOption.LIST_ACCOUNTS;
            case 7 -> MenuOption.STATEMENT;
            case 8 -> MenuOption.REPORT;
            case 9 -> MenuOption.BATCH;
            default -> null;
        };
    }
    private static void printMenu() {
        System.out.println("1. Open Account  2. Deposit  3. Withdraw  4. Transfer  5. Exit");
        System.out.println("6. List Accounts  7. Statement  8. Report  9. Batch Deposits");
        System.out.print("Choose: ");
    }
}
