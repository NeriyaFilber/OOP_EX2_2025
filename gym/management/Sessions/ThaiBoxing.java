package gym.management.Sessions;

import gym.customers.Instructor;

/**
 * The {@code ThaiBoxing} class represents a specialized session type focused on Thai Boxing training.
 * It extends the {@code Session} class and provides specific details such as the cost per session,
 * the maximum number of participants, and the session type.
 */
public class ThaiBoxing extends Session {

    /**
     * The cost of a single Thai Boxing session.
     */
    public static final int SESSION_COST = 100;

    /**
     * The maximum number of participants allowed in a Thai Boxing session.
     */
    public static final int MAX_PARTICIPANT = 20;

    /**
     * Constructs a new {@code ThaiBoxing} session with the specified date, forum type, and instructor.
     *
     * @param date       the date of the session in the format "dd-MM-yyyy HH:mm".
     * @param forumType  the forum type of the session (e.g., All, Female, Seniors, Male).
     * @param instructor the instructor leading the session.
     */
    public ThaiBoxing(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
    }

    /**
     * Retrieves the type of the session.
     *
     * @return the {@code SessionType.ThaiBoxing} constant representing the Thai Boxing session type.
     */
    @Override
    public SessionType getType() {
        return SessionType.ThaiBoxing;
    }

    /**
     * Retrieves the cost of the Thai Boxing session.
     *
     * @return the cost of the session as an integer.
     */
    @Override
    public int getCost() {
        return SESSION_COST;
    }

    /**
     * Retrieves the maximum number of participants allowed in the session.
     *
     * @return the maximum number of participants as an integer.
     */
    @Override
    public int maxNumOfParticipant() {
        return MAX_PARTICIPANT;
    }

    /**
     * Returns a string representation of the Thai Boxing session, including the session type,
     * session details from the {@code Session} superclass, and the maximum number of participants.
     *
     * @return a formatted string containing session details.
     */
    @Override
    public String toString() {
        return String.format("Session Type: %s | ", SessionType.ThaiBoxing) + super.toString() + "/" + MAX_PARTICIPANT;
    }
}