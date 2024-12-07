package gym.Exception;

public class DuplicateClientException extends Exception {
    public DuplicateClientException() {
        System.out.println("Error: The client is already registered");
    }
}
