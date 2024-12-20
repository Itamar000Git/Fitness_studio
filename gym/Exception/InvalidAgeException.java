package gym.Exception;

// Custom exception for invalid age
public class InvalidAgeException extends Exception {
    public String getMessage(){
        return "Error: Client must be at least 18 years old to register";
    }
}

