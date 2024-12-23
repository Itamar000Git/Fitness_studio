package gym.customers;


/**
 * this interface represent the Observer, the client want to get update on every notify from gym secretary, when
 * the client implements this interface  he promises himself that when there is an update from the gym, he will
 * immediately receive it and will not have to check himself every moment whether there is a new message.
 */
public interface notifications {
    void update(String s1);

}
