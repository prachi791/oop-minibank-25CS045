class Book extends Media {

    public Book(String title, int lateDays) {
        super(title, lateDays);
    }

    public double calculateLateFee() {
        return lateDays * 2.0;
    }
}

class DVD extends Media {

    public DVD(String title, int lateDays) {
        super(title, lateDays);
    }

    public double calculateLateFee() {
        return lateDays * 5.0;
    }
}

class Magazine extends Media {

    public Magazine(String title, int lateDays) {
        super(title, lateDays);
    }

    public double calculateLateFee() {
        return lateDays * 1.0;
    }
}

public class Driver {

    public static void main(String[] args) {

        Media[] returnedMedia = {
            new Book("Java Programming", 3),
            new DVD("Interstellar", 2),
            new Magazine("Tech Today", 5)
        };

        double totalFee = 0;

        System.out.println("=== Media Late Fee Report ===");

        for (Media media : returnedMedia) {
            double fee = media.calculateLateFee();

            System.out.println(
                media.getTitle() + " | Late Fee: Rs. " + fee
            );

            totalFee += fee;
        }

        System.out.println("-----------------------------");
        System.out.println("Total Late Fees: Rs. " + totalFee);
    }
}
