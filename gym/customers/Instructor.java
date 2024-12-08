package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor extends Person {
    private ArrayList <SessionType> qualifiedList;
    private int payrate;

    public Instructor(Person p1 ,int p_rate, ArrayList<SessionType> arr) {
        super(p1);
        payrate=p_rate;
        qualifiedList=new ArrayList<>();
        qualifiedList.addAll(arr);
    }

    public void addSession(SessionType s){
        qualifiedList.add(s);
    }
    public ArrayList<SessionType> getqualifiedList(){
        return qualifiedList;
    }




}
