package gym.management.Sessions;

public class Ninja extends Session{
    /**
     * Retrieves the type of the session.
     *
     * @return the type of the session as a {@link SessionType}.
     */
    @Override
    public SessionType getType() {
        return null;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public int numOfParticipant() {
        return 0;
    }
}
