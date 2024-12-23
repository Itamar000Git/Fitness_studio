package gym.management;

import gym.customers.Person;
public class Gym{
    private Secretary sec;
    private int balance;

    private String name;


    private static Gym instance; // Singleton instance of the Gym class

    // Private constructor to enforce Singleton pattern
    private Gym(){
        this.balance=0;
    }

    // Retrieve the singleton instance of the Gym class and create the first onw if needed.
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

    //getters & setters

    // If a secretary already exists, revoke their access, then create and assign a new secretary using the Factory class
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

    //Prints and returns all the details on the gym.
    @Override
    public String toString() {System.out.println("Gym Name: "+instance.name);
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
        return instance.getSecretary().getSesList().get(instance.getSecretary().getSesList().size()-1).toString();
    }
}
