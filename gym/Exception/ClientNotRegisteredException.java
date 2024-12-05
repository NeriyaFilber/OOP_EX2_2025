package gym.Exception;

public class ClientNotRegisteredException extends Throwable {
    private static ClientNotRegisteredException _instanceFalse = new ClientNotRegisteredException();
    private static ClientNotRegisteredSessionException _instanceTrue = new ClientNotRegisteredSessionException();
    @Override
    public String getMessage() {
        return "Error: Registration is required before attempting to unregister";
    }
    public static ClientNotRegisteredException getInstance(boolean isSession){
        if (isSession){
            return _instanceTrue;
        }
        return _instanceFalse;
    }
}
