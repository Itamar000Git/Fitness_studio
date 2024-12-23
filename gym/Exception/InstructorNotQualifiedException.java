package gym.Exception;

/**
 * This exception throws if the secretary tries to assign unqualified instructor to a lesson.
 */
public class InstructorNotQualifiedException extends Exception {

    public String getMessage(){
        return "Error: Instructor is not qualified to conduct this session type.";
    }
}
