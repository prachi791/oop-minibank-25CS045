public class ParkingLot {

    private int twoWheelers;
    private int fourWheelers;

    private final int twoCap;
    private final int fourCap;

    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
        this.twoWheelers = 0;
        this.fourWheelers = 0;
    }

    public boolean park(String type) {
        if (type.equalsIgnoreCase("two")) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Two-wheeler parked. Fee: Rs. 20");
                return true;
            } else {
                System.out.println("Two-wheeler parking Full");
                return false;
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Four-wheeler parked. Fee: Rs. 40");
                return true;
            } else {
                System.out.println("Four-wheeler parking Full");
                return false;
            }
        } else {
            System.out.println("Invalid vehicle type");
            return false;
        }
    }

    public void leave(String type) {
        if (type.equalsIgnoreCase("two")) {
            if (twoWheelers > 0) {
                twoWheelers--;
                System.out.println("Two-wheeler left.");
            } else {
                System.out.println("No two-wheelers to leave.");
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (fourWheelers > 0) {
                fourWheelers--;
                System.out.println("Four-wheeler left.");
            } else {
                System.out.println("No four-wheelers to leave.");
            }
        } else {
            System.out.println("Invalid vehicle type");
        }
    }

    public void displayOccupancy() {
        System.out.println("Two-wheelers: " + twoWheelers + "/" + twoCap);
        System.out.println("Four-wheelers: " + fourWheelers + "/" + fourCap);
    }

    public static long getRevenue() {
        return revenue;
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 1);

        System.out.println("--- Parking Lot Simulation ---");

        lot.park("two");
        lot.park("two");
        lot.park("two");   // Full: rejected

        lot.park("four");
        lot.park("four");  // Full: rejected

        lot.displayOccupancy();

        lot.leave("two");
        lot.leave("four");
        lot.leave("four"); // Cannot go below zero

        lot.park("two");

        System.out.println("\n--- Final Occupancy ---");
        lot.displayOccupancy();

        System.out.println("Total Revenue: Rs. " + getRevenue());
    }
}

