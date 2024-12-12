package gym.Exception;

// Custom exception for invalid age
public class InvalidAgeException extends Exception {
    // Constructor that accepts a custom message
//    public InvalidAgeException() {
//        System.out.println("Error: gym.customers.Client must be at least 18 years old to register");
//    }
    public String getMessage(){
        return "Error: Client must be at least 18 years old to register";
    }
}

