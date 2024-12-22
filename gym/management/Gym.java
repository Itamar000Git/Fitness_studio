package gym.management;

import gym.customers.Person;
import gym.customers.factory;

import java.util.ArrayList;

public class Gym{
    private Secretary sec;
    private int balance;

    private String name;

    private static Gym instance= new Gym();

    private Gym(){
        this.balance=0;
        this.name="CrossFit"; //neww


    }

    public static Gym getInstance(){
        if (instance==null){
         synchronized(Gym.class){
             if (instance==null) {
              instance= new Gym();
             }
         }
        }
        return instance;
    }

    public void setSecretary(Person p1, int sallary){

        if(this.getSecretary()!=null){
            this.getSecretary().setAccess(false);
        }
        this.sec= Secretary.myContructor(p1,sallary);

    }
    public Secretary getSecretary(){
        return this.sec;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
       // ArrayList<String> str = new ArrayList<>();
        System.out.println("Gym Name: "+instance.name);
        System.out.println("Gym Secretary: "+instance.getSecretary().toString());
        System.out.println("Gym Balance: "+instance.getBalance()+"\n");

        System.out.println("Clients Data:");
        for (int i=0;i<instance.getSecretary().getAllClients().size();i++) {
            System.out.println(instance.getSecretary().getAllClients().get(i).toString());
        }
        System.out.println("");
        System.out.println("Employees Data:");
        for (int i=0;i<instance.getSecretary().getinsArr().size();i++) {
            System.out.println(instance.getSecretary().getinsArr().get(i).toString());
        }
        System.out.println(instance.getSecretary().toString()+"\n");

        System.out.println("Sessions Data:");
        for (int i=0; i<instance.getSecretary().getSesList().size()-1;i++){
            System.out.println(instance.getSecretary().getSesList().get(i).toString());
        }
        //System.out.print();

        return instance.getSecretary().getSesList().get(instance.getSecretary().getSesList().size()-1).toString();
    }
}
