package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;


import java.util.ArrayList;

public class NinjaSession extends Session{
    private final int capacity = 5;
    private final int cost=150;
    private final static SessionType type=SessionType.Ninja;
    private ForumType forum;
    private String date;
    private Instructor i;
    private int part;//participant
    private ArrayList<Client> partArr;
    // constructor for Ninja session
    private NinjaSession(String date, ForumType forum, Instructor i) {
        this.date=date;
        this.forum=forum;
        this.i=i;
        this.partArr=new ArrayList<>();
        this.part=0;
        i.setSessionCount(i.getSessionCount()+1);
    }
    // Factory method for creating a new NinjaSession object and prevent from other class creates new object with "new".
    public static NinjaSession NinjaSessionConst(String date, ForumType forum, Instructor i){
        return new NinjaSession(date,forum,i);
    }

    //Getters & Setters
    @Override
    public int getCost(){
        return cost;
    }
    @Override
    public int getCapacity(){
        return capacity;
    }
    @Override
    public Instructor getI() {
        return i;
    }
    @Override
    public ForumType getForum() {
        return forum;
    }
    @Override
    public int getPart() {
        return part;
    }
    @Override
    public void setPart(){
        this.part++;
    }
    @Override
    public String getDate() {
        return date;
    }
    @Override
    public ArrayList<Client> getPartArr() {
        return partArr;
    }
    @Override
    public SessionType getType(){
        return this.type;
    }

    // Overrides the toString method to provide a string of session details
    @Override
    public String toString() {
        String str=(this.part+"/"+this.capacity);
        return ("Session Type: " + this.getType() + " | Date: " + this.getDate() + " | Forum: " + this.getForum() + " | Instructor: " + this.getI().getName() + " | Participants: " + str);
    }
}
