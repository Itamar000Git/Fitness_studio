package gym.management;
import gym.*;
import gym.customers.Client;
import gym.customers.Gender;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

//neww
public class factory {
    public static Person createPerson(String name, int balance, Gender gen, String b_day) {

        return new Person(name, balance, gen, b_day);
    }

    public static Instructor createInstructor(Person p1 , int p_rate, ArrayList<SessionType> arr) {

        return new Instructor(p1,p_rate,arr);
    }

    public static Secretary createSecretary(Person p1, int sal) {

        return new Secretary(p1,sal);
    }

    public static Session createSession(SessionType type, String date, ForumType forum, Instructor i) {

        return new Session(type,date,forum,i);
    }
    public static Client createClient(Person p1){
        return new Client(p1);
    }
//endneww

}
