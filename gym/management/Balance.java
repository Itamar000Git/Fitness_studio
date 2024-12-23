package gym.management;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;

/**
 * This class provides static methods to synchronize the financial balance between clients, instructors, and secretaries.
   It ensures consistency of balance when an entity (client, instructor, secretary) has overlapping roles.
   For example, if a person is both a client and an instructor, their balances will be synchronized to reflect the same value.
   This synchronization helps maintain accurate financial records and avoids discrepancies.
 */
public class Balance {

    // Syncs client's balance with a generic person
    public static void syncPerBalance(Client c, Person p){
        p.setBalance(c.getBalance());
    }
    // Syncs client's balance with an instructor
    public static void syncinsBalance(Client c, Instructor ins){
        ins.setBalance(c.getBalance());
    }
    // Syncs client's balance with a secretary
    public static void syncsecBalance(Client c, Secretary sec){ //neww
        sec.setBalance(c.getBalance());
    }
    // Syncs instructor's balance with the client
    public static void syncClientBalance(Client c, Instructor ins){
        c.setBalance(ins.getBalance());
    }
    // Syncs secretary's balance with the client
    public static void syncClientBalance(Client c, Secretary sec){ //neww
        c.setBalance(sec.getBalance());
    }

}
