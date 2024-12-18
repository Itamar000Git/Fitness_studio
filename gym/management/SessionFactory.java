package gym.management;

import gym.customers.Instructor;
import gym.management.Sessions.*;

/**
 * defoult = packege class privilege
 */
class SessionFactory {
    public static Session createSession(SessionType type, String date, ForumType forum, Instructor i) {
        Session s = null;
        switch (type){
            case MachinePilates -> s= new MachinePilatesSession(date,forum,i);
            case Pilates -> s= new PilatesSession(date,forum,i);
            case Ninja -> s= new NinjaSession(date,forum,i);
            case ThaiBoxing -> s= new ThaiBoxingSession(date,forum,i);
        }

        return s;
    }
}
