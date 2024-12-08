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

    public Session(SessionType type, String date, ForumType forum, Instructor i){
        this.type=type;
        this.date=date;
        this.forum=forum;
        this.i=i;
        this.partArr=new ArrayList<>();
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
}
