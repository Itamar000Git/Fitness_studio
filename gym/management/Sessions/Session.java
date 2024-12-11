package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Session{
    private int cost;
    private SessionType type;
    private ForumType forum;
    private String date;
    private Instructor i;
    private int part;//participant
    private ArrayList<Client> partArr;
    private int capacity;

    private Session(SessionType type, String date, ForumType forum, Instructor i){
        this.type=type;
        this.date=date;
        this.forum=forum;
        this.i=i;
        this.partArr=new ArrayList<>();
        i.setSessionCount(i.getSessionCount()+1);

        switch (this.type){
            case Ninja -> {
                this.capacity=5;
                this.cost=150;
            }
            case Pilates -> {
                this.capacity=30;
                this.cost=60;
            }
            case ThaiBoxing -> {
                this.capacity=20;
                this.cost=100;
            }
            case MachinePilates -> {
                this.capacity=10;
                this.cost=80;
            }
        }
    }
    private Session(){
    }

    public int getCost() {
        return cost;
    }

    public Instructor getI() {
        return i;
    }

    public int getCapacity() {
        return capacity;
    }

    public ForumType getForum() {
        return forum;
    }

    public int getPart() {
        return part;
    }
    public void setPart(){
        this.part++;
    }
    public String getDate() {
        return date;
    }

    public ArrayList<Client> getPartArr() {
        return partArr;
    }

    public SessionType getType(){
        return this.type;
    }

    public static Session myContructor(SessionType type, String date, ForumType forum, Instructor i){
        return new Session(type,date,forum,i);
    }

    @Override
    public String toString() {
        String str=(this.part+"/"+this.capacity);
        return (this.getClass().getName() + " Type: " + this.getType() + " | Date: " + this.getDate() + " | Forum: " + this.getForum() + " | " + this.getI().getClass().getName()
                + ": " + this.getI().getName() + " | Participants: " + str);
    }
}
