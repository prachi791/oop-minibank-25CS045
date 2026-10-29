package util;

import model.Account;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class StatePersister {
    public static void save(Account[] accounts, Path file) throws IOException {
        Path parent = file.getParent();
        if (parent != null) java.nio.file.Files.createDirectories(parent);
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            output.writeObject(accounts);
        }
    }
    public static Account[] load(Path file) throws IOException, ClassNotFoundException {
        if (!java.nio.file.Files.exists(file)) return new Account[0];
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            return (Account[]) input.readObject();
        }
    }
}
