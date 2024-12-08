package gym.Exception;

public class InstructorNotQualifiedException extends Exception {

    public InstructorNotQualifiedException() {
            System.out.println("Error: gym.customers.Instructor is not qualified to conduct this session type.");
    }
}
