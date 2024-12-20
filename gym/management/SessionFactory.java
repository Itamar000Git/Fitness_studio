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
            case MachinePilates :
                s= new MachinePilatesSession(date,forum,i);
                break;
            case Pilates :
                s= new PilatesSession(date,forum,i);
                break;
            case Ninja :
                s= new NinjaSession(date,forum,i);
                break;
            case ThaiBoxing :
                s= new ThaiBoxingSession(date,forum,i);
                break;
            default:
                throw new IllegalArgumentException("This gym don't have this kind of session");
        }

        return s;
    }
}
