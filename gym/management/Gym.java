package gym.management;

import gym.customers.Person;

public class Gym{
    private Secretary sec;
    private int balance;

    private String name;

    private static Gym instance= new Gym();

    private Gym(){
        this.balance=20000;
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
        //we need to make sure there is only one sec and the old ond cant do any changes.
        this.sec=factory.createSecretary(p1,sallary);


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
}
