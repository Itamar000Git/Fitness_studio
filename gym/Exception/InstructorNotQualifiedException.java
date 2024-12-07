package gym.Exception;

public class InstructorNotQualifiedException extends Exception {

    public InstructorNotQualifiedException(String message) {
            System.out.println("Error: Instructor is not qualified to conduct this session type.");
    }
}
