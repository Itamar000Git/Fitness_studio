package gym.customers;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

import java.util.ArrayList;

public class Client extends Person implements notifications {
    private ArrayList<String> notifications;
    //private Person c1;

    private ArrayList<Session> personalSessionList;
    private ArrayList<ForumType> myForums;

    private Client(Person p1){
        super(p1);
        this.personalSessionList=new ArrayList<>();
        this.myForums=new ArrayList<>();
        this.notifications=new ArrayList<>(); //neww
        updateMyforum();
    }
    private Client(){
        super();
    }


    public String getNotifications(){
        return (notifications != null) ? notifications.toString() : "No notifications.";
    }
    public void setNotifications(String str){
        notifications.add(str);
    }

    public void addToMySession (Session s){
        if (!(personalSessionList.contains(s))){
            personalSessionList.add(s);
        }

    }

    public ArrayList<Session> getpersonalSessionList(){
        return personalSessionList;
    }
    public ArrayList<ForumType> getMyForums(){
        return myForums;
    }

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

    public static Client myContructor(Person p1){
        return new Client(p1);
    }


    @Override
    public void update(String s1) {
        this.setNotifications(s1);

    }
    @Override
    public String toString(){
        return ("ID: "+this.getID()+" | Name: "+this.getName()+" | Gender: "+ this.getGen()+
                " | Birthday: "+this.getB_day()+" | Age: "+this.getAge()+" | Balance: "+ this.getBalance());
    }



}
