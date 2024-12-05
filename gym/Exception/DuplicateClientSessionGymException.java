package gym.Exception;

public class DuplicateClientSessionGymException extends Throwable implements DuplicateClientException {
    @Override
    public String getMessage(){

        return "Error: The client is already registered for this lesson";
    }
    @Override
    public static DuplicateClientGymException getInstance(){
        if (_instance == null){
            _instance = new DuplicateClientGymException();
        }
        return _instance;
    }
