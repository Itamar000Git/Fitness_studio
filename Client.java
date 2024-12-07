import java.util.ArrayList;

public class Client extends Person {
    private ArrayList<String> notifications;
    private Person c1;
    public Client(Person p1){
        super(p1);
        this.c1=p1;
    }

    public String getNotifications(){
        return (notifications != null) ? notifications.toString() : "No notifications.";
    }



}
