package gym.management.Sessions;

import gym.customers.Instructor;

public class ThaiBoxing extends Session{
    public static final int SESSION_COST = 100;
    public static final int MAX_PARTICIPANT = 20;


    public ThaiBoxing(String date, ForumType forumType, Instructor instructor){

        super(date, forumType, instructor);

    }
    /**
     * Retrieves the type of the session.
     *
     * @return the type of the session as a {@link SessionType}.
     */
    @Override
    public SessionType getType() {
        return SessionType.ThaiBoxing;
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
        return String.format("Session Type: %s | ", SessionType.ThaiBoxing) + super.toString() + "/" + MAX_PARTICIPANT;
    }
}
