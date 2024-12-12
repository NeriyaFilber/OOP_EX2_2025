package gym.management.Sessions;

import gym.customers.Instructor;

public class Ninja extends Session{
    public static final int SESSION_COST = 150;
    public static final int MAX_PARTICIPANT = 5;

    public Ninja(String date, ForumType forumType, Instructor instructor){
        super(date, forumType, instructor);
    }
    /**
     * Retrieves the type of the session.
     *
     * @return the type of the session as a {@link SessionType}.
     */
    @Override
    public SessionType getType() {
        return SessionType.Ninja;
    }

    @Override
    public int getCost() {
        return SESSION_COST;
    }

    @Override
    public int maxNumOfParticipant() {
        return MAX_PARTICIPANT;
    }
    @Override
    public String toString() {
        return String.format("Session Type: %s | ", SessionType.Ninja) + super.toString() + "/" + MAX_PARTICIPANT;
    }
}
