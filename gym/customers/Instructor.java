package gym.customers;

import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor extends Person {
    private ArrayList <SessionType> qualifiedList;
    private int payrate;
    private int sessionCount;

    //private constructor of Instructor
    private Instructor(Person p1 ,int p_rate, ArrayList<SessionType> arr) {
        super(p1);
        this.payrate=p_rate;
        this.qualifiedList=new ArrayList<>();
        this.qualifiedList.addAll(arr);
        this.sessionCount=0;
    }
    // Factory method for creating a new Client object
    public static Instructor myContructor(Person p1 ,int p_rate, ArrayList<SessionType> arr){
        return new Instructor(p1,p_rate,arr);
    }

    //getters & setters
    public ArrayList<SessionType> getqualifiedList(){
        return qualifiedList;
    }

    public int getSessionCount() {
        return sessionCount;
    }

    public void setSessionCount(int c) {
        sessionCount=c;
    }

    public int getPayrate() {
        return payrate;
    }

    // Overrides the toString method to provide a string of Instructor details
    @Override
    public String toString(){
        String str=(String) this.getqualifiedList().get(0).toString();

        for (int i=1; i<this.getqualifiedList().size(); i++){
            str =str + ", "+ (String) this.getqualifiedList().get(i).toString();
        }
        return ("ID: "+this.getID()+" | Name: "+this.getName()+" | Gender: "+ this.getGen()+
                " | Birthday: "+this.getB_day()+" | Age: "+this.getAge()+" | Balance: "+ this.getBalance()+" | Role: Instructor | Salary per Hour: "+ this.payrate+ " | Certified Classes: " +str);
    }

}
