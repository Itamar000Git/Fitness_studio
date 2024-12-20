package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Secretary;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public abstract class Session{

    public Session() {

    }
    abstract public int getCost();
    abstract public int getCapacity();
    abstract public Instructor getI();
    abstract public ForumType getForum();
    abstract public int getPart();
    abstract public void setPart();
    abstract public String getDate();
    abstract public ArrayList<Client> getPartArr();
    abstract public SessionType getType();

}
