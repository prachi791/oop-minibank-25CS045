package service;

import model.Account;
import exception.BankException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class TransactionProcessor {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Account> getAccounts() { return accounts; }
    public void addAccount(Account account) { accounts.put(account.getAccountNumber(), account); }
    public void submit(Runnable task) { executor.execute(task); }
    public void stop() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
    public void submitDeposit(String accountNumber, long amount) {
        submit(() -> {
            Account account = accounts.get(accountNumber);
            if (account != null) try { account.deposit(amount); } catch (BankException e) { throw new RuntimeException(e); }
        });
    }
    public static void transferInOrder(Account first, Account second, long amount) throws BankException {
        Account low = first.getAccountNumber().compareTo(second.getAccountNumber()) < 0 ? first : second;
        Account high = low == first ? second : first;
        synchronized (low) {
            synchronized (high) {
                first.transfer(second, amount);
            }
        }
    }

    public static void transferOppositeOrder(Account source, Account destination, long amount) throws BankException {
        synchronized (source) {
            synchronized (destination) {
                source.transfer(destination, amount);
            }
        }
    }

    public static class TransactionBuffer {
        private final String[] values;
        private int count;
        public TransactionBuffer(int capacity) { values = new String[capacity]; }
        public synchronized void put(String value) throws InterruptedException {
            while (count == values.length) wait();
            values[count++] = value;
            notifyAll();
        }
        public synchronized String take() throws InterruptedException {
            while (count == 0) wait();
            String value = values[0];
            System.arraycopy(values, 1, values, 0, --count);
            notifyAll();
            return value;
        }
    }

    public static void runProducerConsumer(List<String> transactions) throws InterruptedException {
        TransactionBuffer buffer = new TransactionBuffer(3);
        Thread producer = new Thread(() -> {
            try {
                for (String transaction : transactions) buffer.put(transaction);
                buffer.put(null);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                String transaction;
                while ((transaction = buffer.take()) != null) System.out.println("Processed: " + transaction);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
