package gym.Exception;

/**
 * This exception throws in two conditions:
 * 1. If the secretary tries to unregister client from the gym that is not registered.
 * 2. If the secretary tries to register client to lesson that is not register to the gym.
 */
public class ClientNotRegisteredException extends Exception {
    String err;

    /**
     * This exception get the thrown string as input because there is two string to the same exception
     * @param err
     */
    public ClientNotRegisteredException(String err) {
           this.err=err;
    }

    public String getMessage(){
        return this.err;
    }
}
