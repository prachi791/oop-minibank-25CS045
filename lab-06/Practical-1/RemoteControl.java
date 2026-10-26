interface Switchable
{
    void on();
    void off();

    default void toggle()
    {
        if(this instanceof Fan)
            off();
        else if(this instanceof Light)
        off();
        else
        on();
    }
}

interface SwitchDecider {
    boolean maySwitchOn(Switchable device, int hour);
}

class Fan implements Switchable {
    public void on() {
        System.out.println("Fan ON");
    }

    public void off() {
        System.out.println("Fan OFF");
    }
}

class Light implements Switchable {
    public void on() {
        System.out.println("Light ON");
    }

    public void off() {
        System.out.println("Light OFF");
    }
}

public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] devices = {new Fan(), new Light()};

        for (Switchable device : devices) {
            device.toggle();
        }

        SwitchDecider anonymousDecider = new SwitchDecider() {
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 16 && hour <= 22;
            }
        };

        SwitchDecider lambdaDecider = (device, hour) -> hour >= 16 && hour <= 22;

        int hour = 20;

        for (Switchable device : devices) {
            System.out.println("Anonymous: " + anonymousDecider.maySwitchOn(device, hour));
            System.out.println("Lambda: " + lambdaDecider.maySwitchOn(device, hour));
        }

        for (Switchable device : devices) {
            device.toggle();
        }

         hour = 10;

        for (Switchable device : devices) {
            System.out.println("Anonymous: " + anonymousDecider.maySwitchOn(device, hour));
            System.out.println("Lambda: " + lambdaDecider.maySwitchOn(device, hour));
        }
    }
}