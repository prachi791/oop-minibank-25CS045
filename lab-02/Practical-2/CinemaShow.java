class Show{
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    Show(String title, int capacity)
    {
        this.title = title;
        this.capacity = capacity;
        seatsAvailable = capacity;
    }

    Show(String title)
    {
        this(title, 100);
    }

    boolean book(int n)
    {
        if(n <= seatsAvailable)
        {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }

        return false;
    }

    void cancel(int n)
    {
        if((seatsAvailable+n) > capacity)
        {
            System.out.println("Cannot cancel more than booked.");
        }else{
            seatsAvailable += n;
        }
    }

    int getSeatsAvailable()
    {
        return seatsAvailable;
    }

    static int getTotalBooked()
    {
        return totalBooked;
    }
}

public class CinemaShow{
    public static void main(String[] args)
    {
        Show book1 = new Show("Avengers", 50);

        System.out.println(book1.book(20));
        System.out.println("Seats Available: " + book1.getSeatsAvailable());

        System.out.println(book1.book(25));
        System.out.println("Seats Available: " + book1.getSeatsAvailable());

        System.out.println(book1.book(10));
        System.out.println("Seats Available: " + book1.getSeatsAvailable());

        book1.cancel(15);
        System.out.println("Seats Available: " + book1.getSeatsAvailable());

        book1.cancel(30);
        System.out.println("Seats Available: " + book1.getSeatsAvailable());

        System.out.println("Total Booked: " + Show.getTotalBooked());
    }
}