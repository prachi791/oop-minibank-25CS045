import java.util.Scanner;

class Vehicle {
    String number;
    String type;

    Vehicle(String number, String type) {
        this.number = number;
        this.type = type;
    }
}

public class TollBooth {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalToll = 0;
        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;

        while (true) {
            System.out.print("Enter vehicle number (or done to finish): ");
            String number = sc.nextLine().trim();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = sc.nextLine().trim().toLowerCase();

            Vehicle vehicle = new Vehicle(number, type);
            int toll;

            switch (vehicle.type) {
                case "bike":
                    toll = 20;
                    bikeCount++;
                    break;

                case "car":
                    toll = 50;
                    carCount++;
                    break;

                case "truck":
                    toll = 150;
                    truckCount++;
                    break;

                default:
                    System.out.println("Invalid vehicle type. Try again.");
                    continue;
            }

            totalToll += toll;

            System.out.println("Vehicle: " + vehicle.number);
            System.out.println("Toll: " + toll);
            System.out.println();
        }

        System.out.println("Total toll: " + totalToll);

        if (bikeCount == 0 && carCount == 0 && truckCount == 0) {
            System.out.println("No vehicles recorded.");
        } else if (carCount >= bikeCount && carCount >= truckCount) {
            System.out.println("Most frequent: car");
        } else if (bikeCount >= truckCount) {
            System.out.println("Most frequent: bike");
        } else {
            System.out.println("Most frequent: truck");
        }

        sc.close();
    }
}
