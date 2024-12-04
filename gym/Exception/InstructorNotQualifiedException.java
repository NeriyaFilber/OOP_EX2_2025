package gym.Exception;

public class InstructorNotQualifiedException extends Throwable {
    @Override
    public String getMessage() {
        return "Error: Instructor is not qualified to conduct this session type.";
    }
}
