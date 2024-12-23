package gym.Exception;
/**
 * This exception throws in two conditions:
 * 1. If the secretary tries to register client to the gym that is already register.
 * 2. If the secretary tries to register client to a lesson that is already registered to.
 */
public class DuplicateClientException extends Exception {
    String err;
    /**
     * This exception get the thrown string as input because there is two string to the same exception
     * @param err
     */
    public DuplicateClientException(String err) {
       this.err=err;
    }
    public String getMessage(){
        return this.err;
    }
}
