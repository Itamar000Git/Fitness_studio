package gym.management;

import gym.customers.Instructor;
import gym.management.Sessions.*;

import static gym.management.Sessions.MachinePilatesSession.MachinePilatesSessionConst;
import static gym.management.Sessions.NinjaSession.NinjaSessionConst;
import static gym.management.Sessions.PilatesSession.PilatesSessionConst;
import static gym.management.Sessions.ThaiBoxingSession.ThaiBoxingSessionConst;

/**
 * This class is the session factory, this is the only place that creates all kind of session.
 * This class use switch case by the session type.
 * This class use the public methods in each class for creating the object.
 * notice that this class is can be use only by the same package methods.
 */
class SessionFactory {
    static Session createSession(SessionType type, String date, ForumType forum, Instructor i) {
        Session s = null;
        switch (type){
            case MachinePilates :
                s= MachinePilatesSessionConst(date,forum,i);
                break;

            case Pilates :
                s= PilatesSessionConst(date,forum,i);
                break;
            case Ninja :
                s= NinjaSessionConst(date,forum,i);
                break;
            case ThaiBoxing :
                s= ThaiBoxingSessionConst(date,forum,i);
                break;
            default:
                throw new IllegalArgumentException("This gym don't have this kind of session");
        }

        return s;
    }
}
