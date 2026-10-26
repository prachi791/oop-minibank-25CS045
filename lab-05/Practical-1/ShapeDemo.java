abstract class Shape {
   public abstract double area();
}

 class Circle extends Shape
{
    private int radius;
    Circle(int radius)
    {
        this.radius = radius;
    }

    public double area()
    {
        return 3.14 * radius * radius;
    }
}

 class Rectangle extends Shape
{
    private int length;
    private int breadth;

    Rectangle(int length, int breadth)
    {
        this.length = length;
        this.breadth = breadth;
    }

    public double area()
    {
        return length * breadth;
    }
}

class Triangle extends Shape
{
    private int base;
    private int height;

    Triangle(int base, int height)
    {
        this.base = base;
        this.height = height;
    }

    public double area()
    {
        return 0.5 * base * height;
    }
}

public class ShapeDemo
{
    public static void main(String[] args)
    {
        double total = 0;
        double largest = -1;
        String label="";

        Shape[] shapes = {
            new Circle(5), 
            new Rectangle(4,6),
            new Triangle(5,6),
            new Circle(2)
        };

        for(Shape s : shapes)
        {
            System.out.println("Area: " + s.area());
            total += s.area();

            if(largest < s.area())
            {
                largest = s.area();
                label = s.getClass().getSimpleName();
            }
        }

        System.out.println("the total area is: " + total);
        System.out.println("the largest area is: " + largest + " and it is of " + label);
    }
}