package gym.customers;

import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor extends Person {
    private ArrayList <SessionType> qualifiedList;
    private int payrate;
    private int sessionCount;

    private Instructor(Person p1 ,int p_rate, ArrayList<SessionType> arr) {
        super(p1);
        this.payrate=p_rate;
        qualifiedList=new ArrayList<>();
        qualifiedList.addAll(arr);
        this.sessionCount=0;
    }
    private Instructor(){
        super();
    }

    public void addSession(SessionType s){

        qualifiedList.add(s);
    }
    public ArrayList<SessionType> getqualifiedList(){
        return qualifiedList;
    }

    public static Instructor myContructor(Person p1 ,int p_rate, ArrayList<SessionType> arr){
        return new Instructor(p1,p_rate,arr);
    }

    public int getSessionCount() {
        return sessionCount;
    }

    public void updatedSallery(int sal){
        this.setBalance(this.getBalance()+sal);
    }

    public void setSessionCount(int c) {
        sessionCount=c;
    }

    public int getPayrate() {
        return payrate;
    }
    @Override
    public String toString(){
        String str=(String) this.getqualifiedList().get(0).toString();

        for (int i=1; i<this.getqualifiedList().size(); i++){
            str =str + ", "+ (String) this.getqualifiedList().get(i).toString();
        }
        return ("ID: "+this.getID()+" | Name: "+this.getName()+" | Gender: "+ this.getGen()+
                " | Birthday: "+this.getB_day()+" | Age: "+this.getAge()+" | Balance: "+ this.getBalance()+" | Role: Instructor | Salary per Hour: "+ this.payrate+ " | Certified Classes: " +str);
    }

    public void setp_rate(int pay){
        this.payrate=pay;
    }
    public void setarr(ArrayList<SessionType> arr){

        this.qualifiedList.addAll(arr);
    }

    //ID: 1114 | Name: Shachar | gym.customers.Gender: Female | Birthday: 09-04-1958 | Age: 66 | Balance: 290 | Role: gym.customers.Instructor | Salary per Hour: 70 | Certified Classes: ThaiBoxing, MachinePilates

}
