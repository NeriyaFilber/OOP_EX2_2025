package gym.management.Sessions;

import gym.customers.Instructor;

/**
 * The {@code MachinePilates} class represents a gym session type focused on machine-based Pilates exercises.
 * It extends the {@code Session} class and provides specific details for Machine Pilates sessions,
 * including the session cost, maximum number of participants, and session type.
 */
public class MachinePilates extends Session {

    /**
     * The cost of a single Machine Pilates session.
     */
    public static final int SESSION_COST = 80;

    /**
     * The maximum number of participants allowed in a Machine Pilates session.
     */
    public static final int MAX_PARTICIPANT = 10;

    /**
     * Constructs a new {@code MachinePilates} session with the specified date, forum type, and instructor.
     *
     * @param date       the date of the session.
     * @param forumType  the forum type of the session (e.g., All, Female, Seniors, Male).
     * @param instructor the instructor leading the session.
     */
    public MachinePilates(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
    }

    /**
     * Retrieves the type of the session.
     *
     * @return the {@code SessionType.MachinePilates} constant representing the Machine Pilates session type.
     */
    @Override
    public SessionType getType() {
        return SessionType.MachinePilates;
    }

    /**
     * Retrieves the cost of the Machine Pilates session.
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
     * Returns a string representation of the Machine Pilates session, including session type,
     * basic details from the {@code Session} superclass, and the maximum number of participants.
     *
     * @return a formatted string containing session details.
     */
    @Override
    public String toString() {
        return String.format("Session Type: %s | ", SessionType.MachinePilates) + super.toString() + "/" + MAX_PARTICIPANT;
    }
}
