package gym.customers;
import gym.customers.Client;
import gym.customers.Gender;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Gym;
import gym.management.Secretary;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;


public class factory {

//    public static Person createPerson(String name, int balance, Gender gen, String b_day) {
//
//        return new Person(name, balance, gen, b_day);
//    }

    public static Instructor createInstructor(Person p1, int p_rate, ArrayList<SessionType> arr) {
        return Instructor.myContructor(p1, p_rate, arr);
    }

    public static Secretary createSecretary(Person p1, int sal) {
        return Secretary.myContructor(p1,sal);
    }

    public static Session createSession(SessionType type, String date, ForumType forum, Instructor i) {

        return Session.myContructor(type,date,forum,i);
    }

    public static Client createClient(Person p1) {
        return Client.myContructor(p1); //neww
    }
}

