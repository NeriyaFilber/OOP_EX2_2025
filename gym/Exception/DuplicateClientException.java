package gym.Exception;

public class DuplicateClientException extends Throwable {
    @Override
    public String getMessage() {
        return "Error: The client is already registered";
    }
}
