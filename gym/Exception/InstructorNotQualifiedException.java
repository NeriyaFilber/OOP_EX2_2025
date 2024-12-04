package gym.Exception;

public class InstructorNotQualifiedException extends Throwable {
    private static InstructorNotQualifiedException _instance;
    @Override
    public String getMessage() {
        return "Error: Instructor is not qualified to conduct this session type.";
    }
    public static InstructorNotQualifiedException getInstance(){
        if(_instance == null){
            _instance = new InstructorNotQualifiedException();
        }
        return _instance;
    }
}
