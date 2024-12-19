package gym.management.Sessions;

import gym.customers.Instructor;

/**
 * The {@code SessionFactory} class is responsible for creating instances of various {@link Session} types.
 * This class implements the <b>Factory Design Pattern</b>, allowing for the creation of specific session types
 * without exposing the instantiation logic to the client.
 *
 * <p>This pattern provides a centralized and flexible way of creating sessions based on the provided {@link SessionType}.
 */
public class SessionFactory {

    /**
     * Creates a new {@link Session} instance based on the specified parameters.
     *
     * @param sessionType The type of session to create (e.g., Ninja, Pilates, MachinePilates, ThaiBoxing).
     * @param s           The date of the session in the format "dd-MM-yyyy HH:mm".
     * @param forumType   The forum type of the session (e.g., All, Female, Seniors, Male).
     * @param instructor  The instructor leading the session.
     * @return A specific instance of {@link Session}, determined by the {@code sessionType}.
     */
    public static Session createSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) {
        return switch (sessionType) {
            case Ninja -> new Ninja(s, forumType, instructor);
            case Pilates -> new Pilates(s, forumType, instructor);
            case ThaiBoxing -> new ThaiBoxing(s, forumType, instructor);
            case MachinePilates -> new MachinePilates(s, forumType, instructor);
        };
    }
}