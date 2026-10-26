abstract class Employee
{
    String name;
    int id;

    Employee(String name, int id)
    {
        this.name = name;
        this.id = id;
    }
    public abstract int monthlySalary();
}

class FullTime extends Employee
{
    int fixed;

    FullTime(String name, int id, int fixed)
    {
        super(name,id);
        this.fixed = fixed;
    }

    public int monthlySalary()
    {
        return fixed;
    }
}

class PartTime extends Employee
{
    int hours;
    int rate;

    PartTime(String name, int id, int hours, int rate)
    {
        super(name,id);
        this.hours = hours;
        this.rate = rate;
    }

     public int monthlySalary()
    {
        return hours * rate;
    }
}

class Intern extends Employee
{
    int stipend;

    Intern(String name, int id, int stipend)
    {
        super(name,id);
        this.stipend = stipend;
    }

     public int monthlySalary()
    {
        return stipend;
    }
}


public class EmployeeSalary
{
    public static void main(String[] args)
    {
        int total = 0;
        Employee[] person = {
            new FullTime("xyz", 20, 120000),
            new PartTime("abc", 3, 8, 13000),
            new Intern("pqr", 5, 10000)
        };

        for(Employee e : person)
        {
            total += e.monthlySalary();
            if(e instanceof Intern)
            {
                System.out.println("Interns don't have salary, they have stipend");
                continue;
            }
            System.out.println("Salary is: " + e.monthlySalary());

        }

        System.out.println("Total Salary is: " + total);
    }
}