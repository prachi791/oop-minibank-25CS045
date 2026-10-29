package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import static java.lang.System.lineSeparator;

public class TransactionLog {
    public static void append(Path file, String line) throws IOException {
        Path parent = file.getParent();
        if (parent != null) Files.createDirectories(parent);
        Files.write(file, (line + lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
