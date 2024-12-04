package gym.Exception;

public class ClientNotRegisteredException extends Throwable {
    @Override
    public String getMessage() {
        return "Error: Registration is required before attempting to unregister";
    }
}
