package gym.Exception;

public class InvalidAgeException extends Throwable {
    private static InvalidAgeException _instance;
    @Override
    public String getMessage() {
        return "Error: Client must be at least 18 years old to register";
    }

    public static InvalidAgeException getInstance(){
        if(_instance == null){
            _instance = new InvalidAgeException();
        }
        return _instance;
    }
}
