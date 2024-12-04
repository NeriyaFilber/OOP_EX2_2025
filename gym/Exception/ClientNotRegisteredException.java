package gym.Exception;

public class ClientNotRegisteredException extends Throwable {
    private static ClientNotRegisteredException _instance;
    @Override
    public String getMessage() {
        return "Error: Registration is required before attempting to unregister";
    }
    public static ClientNotRegisteredException getInstance(){
        if (_instance == null){
            _instance = new ClientNotRegisteredException();
        }
        return _instance;
    }
}
