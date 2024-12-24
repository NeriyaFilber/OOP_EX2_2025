package gym.management;

import gym.Exception.InstructorNotQualifiedException;
import gym.customers.Instructor;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionFactory;
import gym.management.Sessions.SessionType;

/**
 * The {@code SessionManagement} class handles the creation and management of gym sessions.
 * It enables the addition of new sessions while ensuring that instructors are qualified for the session types.
 */
class SessionManagement {
    /**
     * An instance of the {@code GymManagementSystem} used to manage gym data.
     */
    private GymManagementSystem gymSystem = GymManagementSystem.getInstance();

    /**
     * Adds a new session to the gym's system.
     * Creates a session based on the provided session type, forum type, and instructor,
     * and logs the action. Throws an exception if the instructor is not qualified to teach the session type.
     *
     * @param sessionType the type of the session (e.g., Yoga, Cardio).
     * @param s           the date or description of the session.
     * @param forumType   the forum type (e.g., Private, Group).
     * @param instructor  the instructor responsible for the session.
     * @return the newly created {@code Session} object.
     * @throws InstructorNotQualifiedException if the instructor is not certified to teach the given session type.
     */
    protected Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if(!gymSystem.getGymInstructors().contains(instructor)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not work in this gym.");
        }
        if (!instructor.getCertifiedClasses().contains(sessionType)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }
        Session session = SessionFactory.createSession(sessionType, s, forumType, instructor);
        gymSystem.addSession(session);

        String mes = new String("Created new session: " + sessionType + " on " + session.getDate() + " with instructor: " + instructor.getName());
        ActionLogManager.getInstance().logAction(mes); // Logs the creation of the new session
        return session;
    }
}