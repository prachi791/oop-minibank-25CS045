package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {
    public static String generate(Path logsFolder, Path reportFile) throws IOException {
        long deposits = 0;
        long withdrawals = 0;
        StringBuilder details = new StringBuilder();
        if (Files.exists(logsFolder)) {
            try (DirectoryStream<Path> files = Files.newDirectoryStream(logsFolder)) {
                for (Path file : files) {
                    if (!Files.isRegularFile(file) || !file.toString().endsWith(".log") || Files.size(file) == 0) continue;
                    try (BufferedReader reader = Files.newBufferedReader(file)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.trim().split("\\s+");
                            if (parts.length >= 3) {
                                try {
                                    long amount = Long.parseLong(parts[2]);
                                    if (parts[0].equals("DEPOSIT")) deposits += amount;
                                    if (parts[0].equals("WITHDRAW")) withdrawals += amount;
                                } catch (NumberFormatException ignored) {
                                }
                            }
                        }
                    }
                    BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
                    details.append(file.getFileName()).append(" size=").append(attributes.size())
                            .append(" modified=").append(attributes.lastModifiedTime()).append('\n');
                }
            }
        }
        String report = "Deposits: " + deposits + "\nWithdrawals: " + withdrawals
                + "\nNet change: " + (deposits - withdrawals) + "\n" + details;
        Path parent = reportFile.getParent();
        if (parent != null) Files.createDirectories(parent);
        Files.writeString(reportFile, report);
        return report;
    }
}
