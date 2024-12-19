package gym.Exception;

public class ClientNotRegisteredException extends Exception {
    String err;
    public ClientNotRegisteredException(String err) {
           this.err=err;
    }

    public String getMessage(){
        return this.err;
    }
}
