package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Secretary;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

/**
 * Abstract class representing a generic session.
   This class serves as a blueprint for different session types,
   and requires all subclasses to implement the specified methods.
 */
public abstract class Session{
    // Generic constructor for the Session class that matches to any session.
    public Session() {
        // Default constructor - can be used by subclasses
    }

    //return the cost of the session.
    abstract public int getCost();

    //return the maximum capacity of participants for the session.
    abstract public int getCapacity();

    //return the instructor assigned to the session.
    abstract public Instructor getI();

    //the forum type where the session will be held (All, senior, women, mans).
    abstract public ForumType getForum();

    //return the current number of participants in the session.
    abstract public int getPart();

    /**
     * Sets the participants for the session.
     * The implementation details depend on the subclass.
     */
    abstract public void setPart();

    //return the date of the session as a String.
    abstract public String getDate();

    //return an array list containing all participants in the session.
    abstract public ArrayList<Client> getPartArr();

    //returns the session type.
    abstract public SessionType getType();

}
