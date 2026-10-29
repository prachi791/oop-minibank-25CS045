package minibank;

import exception.BankException;
import model.Account;
import model.Customer;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.WithdrawRule;
import service.AccountStore;
import service.Repository;
import service.TransactionProcessor;
import util.AnnotationValidator;
import util.CommandParser;
import util.Validator;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Account savings = new SavingsAccount("Riya", 4000, 500);
        Account sameAccount = new SavingsAccount((SavingsAccount) savings);
        System.out.println(savings);
        System.out.println("Equal accounts: " + savings.equals(sameAccount));
        Object value = savings;
        System.out.println("Is account: " + (value instanceof Account));

        Customer customer = new Customer("Riya", "riya@example.com", "9876543210");
        customer.setAddress(new Customer.Address("12 MG Road", "Nadiad", "387001"));
        System.out.println("Customer: " + customer.clone());
        System.out.println("Address city: " + customer.getAddress().getCity());

        System.out.println("Valid mobile: " + Validator.isValidMobile("9876543210"));
        System.out.println("Invalid mobile: " + Validator.isValidMobile("12345"));
        System.out.println("Parsed command: " + CommandParser.parse("DEPOSIT AC0001 500"));

        Account[] typed = {
            new SavingsAccount("Aarav", 10000, 2000),
            new CurrentAccount("Meera", 5000, 3000),
            new FixedDepositAccount("Kabir", 20000)
        };
        for (Account account : typed) System.out.println(account.getClass().getSimpleName() + " interest: " + account.interestRate());
        if (typed[0] instanceof SavingsAccount account) System.out.println("Minimum balance: " + account.getMinBalance());

        WithdrawRule anonymous = new WithdrawRule() {
            @Override public boolean allow(Account account, long amount) {
                try { return account.canWithdraw(amount); } catch (RuntimeException e) { return false; }
            }
        };
        WithdrawRule lambda = (account, amount) -> account.canWithdraw(amount);
        System.out.println("Anonymous withdrawal rule: " + anonymous.allow(savings, 1000));
        System.out.println("Lambda withdrawal rule: " + lambda.allow(savings, 1000));

        Account invalid = new SavingsAccount("Invalid", -1, 0);
        for (String error : AnnotationValidator.validate(invalid)) System.out.println(error);

        Account threaded = new SavingsAccount("Threaded", 0, 0);
        Thread[] workers = new Thread[10];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Thread(new AccountWorker(threaded, 100, 1));
            workers[i].start();
        }
        for (Thread worker : workers) worker.join();
        System.out.println("Concurrent balance: " + threaded.getBalance());

        AccountStore store = new AccountStore();
        for (Account account : typed) store.add(account);
        for (Account account : store.sortedByBalance()) System.out.println("Sorted: " + account);
        Repository<Account> repository = new Repository<>();
        repository.saveAll(typed);
        System.out.println("Repository account: " + repository.findById(typed[0].getAccountNumber()));
        TransactionProcessor.runProducerConsumer(List.of("DEPOSIT AC0001 100", "WITHDRAW AC0001 50"));
    }
}
