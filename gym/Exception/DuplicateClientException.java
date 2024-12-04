package gym.Exception;

public class DuplicateClientException extends Throwable {
    private static DuplicateClientException _instance;
    @Override
    public String getMessage() {
        return "Error: The client is already registered";
    }
    public static DuplicateClientException getInstance(){
        if (_instance == null){
            _instance = new DuplicateClientException();
        }
        return _instance;
    }
}
