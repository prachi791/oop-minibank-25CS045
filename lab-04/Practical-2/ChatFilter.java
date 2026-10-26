import java.util.Scanner;

class ChatLogFilter
{
    public static String[] splitLine(String line)
    {
        String[] parts = line.split(" ", 3);
        if(parts.length <= 2)
        {
            return null;
        }

        return parts;
    } 

    public static boolean hasKeyword(String msg, String keyword)
    {
        return msg.toLowerCase().contains(keyword.toLowerCase());
    }
} 


class ChatFilter
{
    public static void main(String[] args)
    {
        int match = 0;
        StringBuilder report = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the keyword to search: ");
        String key = sc.nextLine();

        String[] logs = {"3:38 Prachi hello there", "3:41 Prachi Good afternoon", "3:42 Prachi good morning aditi" , "3:59 Prachi "};

        for(String line : logs)
        {
            String[] parts = ChatLogFilter.splitLine(line);
            if(parts == null)
            {
                continue;
            }
            if(ChatLogFilter.hasKeyword(parts[2], key))
            {
                match++;
                report.append(parts[0]).append(" ").append(parts[1]).append(" , ").append(parts[2]).append("\n");
            }
            System.out.println(parts[2]);
        }

        System.out.println("match found: " + match + " " + report);
    


    }
}