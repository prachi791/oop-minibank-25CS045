package service;

import exception.InsufficientFundsException;
import model.Account;
import model.SavingsAccount;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankServiceTest {
    @Test
    public void depositIncreasesBalance() throws Exception {
        Account account = new SavingsAccount("Riya", 1000, 0);
        Repository<Account> repository = new Repository<>();
        repository.save(account);
        BankService bank = new BankService(repository, Path.of("target", "test-data", "transactions.log"));
        bank.deposit(account.getAccountNumber(), 500);
        assertEquals(1500, account.getBalance());
    }

    @Test
    public void withdrawalOverLimitThrows() {
        Account account = new SavingsAccount("Riya", 1000, 0);
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(1500));
    }

    @Test
    public void concurrentDepositsKeepCorrectBalance() throws Exception {
        Account account = new SavingsAccount("Riya", 0, 0);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    try { account.deposit(1); } catch (Exception e) { throw new RuntimeException(e); }
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) thread.join();
        assertEquals(10000, account.getBalance());
    }
}
