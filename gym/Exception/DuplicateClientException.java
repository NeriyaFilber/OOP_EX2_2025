package gym.Exception;

public class DuplicateClientException extends Throwable {
    private static DuplicateClientSessionException _instanceTrue = new DuplicateClientSessionException();
    private static DuplicateClientException _instanceFalse = new DuplicateClientException();

    @Override
    public String getMessage() {
        return "Error: The client is already registered";
    }

    public static DuplicateClientException getInstance(boolean isSession) {
        if (isSession) {
            return _instanceTrue;
        }
        return _instanceFalse;
    }
}

