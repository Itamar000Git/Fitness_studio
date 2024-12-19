package gym.Exception;

public class DuplicateClientException extends Exception {
    String err;
    public DuplicateClientException(String err) {
       this.err=err;
    }
    public String getMessage(){
        return this.err;
    }
}
