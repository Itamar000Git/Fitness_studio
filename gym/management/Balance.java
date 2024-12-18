package gym.management;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;

public class Balance {
    public static void syncPerBalance(Client c, Person p){ //neww
        p.setBalance(c.getBalance());
    }
    public static void syncinsBalance(Client c, Instructor ins){
        ins.setBalance(c.getBalance());
    }
    public static void syncsecBalance(Client c, Secretary sec){ //neww
        sec.setBalance(c.getBalance());
    }

    public static void syncClientBalance(Client c, Instructor ins){
        c.setBalance(ins.getBalance());
    }
    public static void syncClientBalance(Client c, Secretary sec){ //neww
        c.setBalance(sec.getBalance());
    }

}
