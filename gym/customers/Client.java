package gym.customers;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

import java.util.ArrayList;

public class Client extends Person implements notifications {
    private ArrayList<String> notifications;
    private ArrayList<Session> personalSessionList;
    private ArrayList<ForumType> myForums;

    //private constructor of client
    private Client(Person p1){
        super(p1);
        this.personalSessionList=new ArrayList<>();  // Initialize the personal session list
        this.myForums=new ArrayList<>();    // Initialize the forums list
        this.notifications=new ArrayList<>();  // Initialize the notifications list
        updateMyforum();    // Update the forums the client belongs them
    }

    //getters & setters
    public String getNotifications(){
        return (notifications != null) ? notifications.toString() : "No notifications.";
    }
    public void setNotifications(String str){
        notifications.add(str);
    }



    public ArrayList<Session> getpersonalSessionList(){
        return personalSessionList;
    }
    public ArrayList<ForumType> getMyForums(){
        return myForums;
    }

    // Adds a session to the personal session list if it isn't already present
    public void addToMySession (Session s){
        if (!(personalSessionList.contains(s))){
            personalSessionList.add(s);
        }

    }
    // Updates the forums list based on the client's attributes (gender and age)
    private void updateMyforum(){
        myForums.add(ForumType.All);
        if (this.getGen()== Gender.Female){
            myForums.add(ForumType.Female);
        } else if (this.getGen()== Gender.Male) {
            myForums.add(ForumType.Male);
        }
        if (this.getAge()>65){
            myForums.add(ForumType.Seniors);
        }
    }

    // Factory method for creating a new Client object
    public static Client myContructor(Person p1){
        return new Client(p1);
    }

    // Implementation of the update method from the notifications interface - the client want to update on any notify of the gym,
    // when he registers to the gym he join to the observer and he gets update on any change
    @Override
    public void update(String s1) {
        this.setNotifications(s1);

    }

    // Overrides the toString method to provide a string of client details
    @Override
    public String toString(){
        return ("ID: "+this.getID()+" | Name: "+this.getName()+" | Gender: "+ this.getGen()+
                " | Birthday: "+this.getB_day()+" | Age: "+this.getAge()+" | Balance: "+ this.getBalance());
    }



}
