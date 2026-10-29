package service;

import exception.AccountNotFoundException;
import exception.BankException;
import exception.InvalidAmountException;
import model.Account;
import model.SavingsAccount;
import util.StatementFormatter;
import util.TransactionLog;
import util.Validator;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class BankService {
    private final Repository<Account> repository;
    private final Path logFile;
    public BankService(Repository<Account> repository, Path logFile) { this.repository = repository; this.logFile = logFile; }

    public Account openAccount(String name, String email, String mobile, long openingBalance) throws BankException {
        if (name == null || name.isBlank() || !Validator.isValidEmail(email) || !Validator.isValidMobile(mobile)) {
            throw new InvalidAmountException("Enter a name, valid email and valid mobile number.");
        }
        if (openingBalance < 0) throw new InvalidAmountException("Opening balance cannot be negative.");
        Account account = new SavingsAccount(name, openingBalance, 0);
        repository.save(account);
        return account;
    }
    public void deposit(String id, long amount) throws BankException {
        Account account = require(id);
        account.deposit(amount);
        log("DEPOSIT", id, amount);
    }
    public void withdraw(String id, long amount) throws BankException {
        Account account = require(id);
        account.withdraw(amount);
        log("WITHDRAW", id, amount);
    }
    public void transfer(String from, String to, long amount) throws BankException {
        Account source = require(from);
        Account destination = require(to);
        source.transfer(destination, amount);
        log("WITHDRAW", from, amount);
        log("DEPOSIT", to, amount);
    }
    public String statement(String id) throws BankException { return StatementFormatter.buildStatement(require(id)); }
    public ArrayList<Account> listAccounts() { return repository.findAll(); }
    public void addAccount(Account account) { repository.save(account); }
    private Account require(String id) throws AccountNotFoundException {
        Account account = repository.findById(id);
        if (account == null) throw new AccountNotFoundException(id);
        return account;
    }
    private void log(String type, String id, long amount) throws BankException {
        try { TransactionLog.append(logFile, type + " " + id + " " + amount); }
        catch (IOException e) { throw new BankException("Could not write transaction log: " + e.getMessage()); }
    }
}
