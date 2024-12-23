package gym.Exception;

/**
 * This exception throws when the secretary tries to register a person under 18 years old to the gym.
 */
public class InvalidAgeException extends Exception {
    public String getMessage(){
        return "Error: Client must be at least 18 years old to register";
    }
}

