package gym.customers;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

import java.util.ArrayList;

public class Client extends Person {
    private ArrayList<String> notifications;
    private Person c1;

    private ArrayList<Session> personalSessionList;
    private ArrayList<ForumType> myForums;

    public Client(Person p1){
        super(p1);
        this.c1=p1;
        this.personalSessionList=new ArrayList<>();
        this.myForums=new ArrayList<>();
        updateMyforum();
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



}
