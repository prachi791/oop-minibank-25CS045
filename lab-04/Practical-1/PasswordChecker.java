import java.util.Scanner;

class Driver
{
    public static boolean HasLength(String pw)
        {
            return (pw.length() >= 8);      // array -> length is property, in String it is method
        }
    
    public static boolean HasUpperCase(String pw)
    {
        return pw.matches(".*[A-Z].*");
    }

    public static boolean HasDigits(String pw)
    {
        return pw.matches(".*[0-9].*");
    }

    public static boolean HasSpecialCharacter(String pw)
    {
        return pw.matches(".*[@$#_*].*");
    }

    public static String GetStrength(String pw)
    {
        int count = 0; // compulsory to initialize the local variable otherwise will get an error as local var doesn't get default value
        if(HasLength(pw)) 
        {
            count++;
        }
        if(HasUpperCase(pw))
        {
            count++;
        }

        if(HasDigits(pw))
        {
            count++;
        }

        if(HasSpecialCharacter(pw))
        {
            count++;
        }

        String st = switch(count)
        {
            case 0,1 -> "Weak";
            case 2,3 -> "Medium";
            case 4 -> "Strong";
            default -> "Invalid";
        };

        return st;
    }
}

class PasswordChecker
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        String password = sc.next();

        System.out.println(Driver.GetStrength(password));

    }
}