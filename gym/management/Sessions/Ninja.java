package gym.management.Sessions;

import gym.customers.Instructor;

/**
 * The {@code Ninja} class represents a specialized gym session type focused on Ninja-style training.
 * It extends the {@code Session} class and provides specific details for Ninja sessions,
 * including the session cost, maximum number of participants, and session type.
 */
public class Ninja extends Session {

    /**
     * The cost of a single Ninja session.
     */
    public static final int SESSION_COST = 150;

    /**
     * The maximum number of participants allowed in a Ninja session.
     */
    public static final int MAX_PARTICIPANT = 5;

    /**
     * Constructs a new {@code Ninja} session with the specified date, forum type, and instructor.
     *
     * @param date       the date of the session.
     * @param forumType  the forum type of the session (e.g., All, Female, Seniors, Male).
     * @param instructor the instructor leading the session.
     */
    public Ninja(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
    }

    /**
     * Retrieves the type of the session.
     *
     * @return the {@code SessionType.Ninja} constant representing the Ninja session type.
     */
    @Override
    public SessionType getType() {
        return SessionType.Ninja;
    }

    /**
     * Retrieves the cost of the Ninja session.
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
     * Returns a string representation of the Ninja session, including session type,
     * basic details from the {@code Session} superclass, and the maximum number of participants.
     *
     * @return a formatted string containing session details.
     */
    @Override
    public String toString() {
        return String.format("Session Type: %s | ", SessionType.Ninja) + super.toString() + "/" + MAX_PARTICIPANT;
    }
}