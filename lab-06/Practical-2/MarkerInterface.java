interface Notifier{
    void send(String message);
}

interface Urgent
{

}

class EmailNotifier implements Notifier, Urgent {
    public void send(String message) {
        System.out.println("Sending email: " + message + "(URGENT)");
    }
}

class MarkerInterface
{
    public static void main(String[] args)
    {
        Notifier email = message -> System.out.println("Email: " + message);
        Notifier sms = message -> System.out.println("SMS: " + message); 
        Notifier UrgentEmail = new EmailNotifier();
        Notifier[] senders = {email, sms, UrgentEmail};

        String message = "This is a notification";
        for(Notifier sender : senders)
        {
            sender.send(message);
            if(sender instanceof Urgent)
            {
                sender.send(message);
            }
        }
    }
}
