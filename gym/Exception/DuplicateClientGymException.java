package gym.Exception;

public class DuplicateClientGymException extends Throwable implements DuplicateClientException {
    private static DuplicateClientGymException _instance;
    @Override
    public String getMessage() {
        return "Error: The client is already registered";
    }
    public static DuplicateClientGymException getInstance(){
        if (_instance == null){
            _instance = new DuplicateClientGymException();
        }
        return _instance;
    }
}
