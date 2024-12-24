package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;

import java.util.ArrayList;

/**
 * The {@code WorkManagement} abstract class defines the operations a gym management system must support.
 */
abstract class WorkManagement {

    /**
     * Registers a new client in the gym.
     *
     * @param person the {@code Person} object representing the client.
     * @return the newly registered {@code Client} object.
     * @throws InvalidAgeException      if the client's age is invalid.
     * @throws DuplicateClientException if the client already exists in the system.
     */
    protected abstract Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException;

    /**
     * Hires a new instructor for the gym.
     *
     * @param person       the {@code Person} representing the instructor.
     * @param salary       the salary of the instructor.
     * @param sessionTypes the session types the instructor is qualified to teach.
     * @return the newly hired {@code Instructor} object.
     */
    protected abstract Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes);

    /**
     * Registers a client to a gym session and updates the gym balance.
     *
     * @param client  the {@code Client} to register.
     * @param session the {@code Session} the client will attend.
     * @throws ClientNotRegisteredException if the client is not registered in the system.
     * @throws DuplicateClientException     if the client is already registered for the session.
     */
    protected abstract void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException;

    /**
     * Unregisters a client from the gym system.
     *
     * @param client the {@code Client} to unregister.
     * @throws ClientNotRegisteredException if the client is not registered in the system.
     */
    protected abstract void unregisterClient(Client client) throws ClientNotRegisteredException;

    /**
     * Pays salaries to all employees, including instructors and the secretary.
     */
    protected abstract void paySalaries();

    /**
     * Retrieves the log of all recorded actions in the gym.
     *
     * @return an {@code ArrayList<String>} of logged actions.
     */
    protected abstract ArrayList<String> get_log();

    /**
     * Adds a new session to the gym's system.
     *
     * @param sessionType the type of session to add.
     * @param s           the date or details of the session.
     * @param forumType   the forum type of the session.
     * @param instructor  the instructor leading the session.
     * @return the created {@code Session} object.
     * @throws InstructorNotQualifiedException if the instructor is not qualified for the session type.
     */
    protected abstract Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException;

    /**
     * Sends a notification related to a session.
     *
     * @param s4 the {@code Session} associated with the notification.
     * @param s  the notification message.
     */
    protected abstract void notify(Session s4, String s);

    /**
     * Sends a notification with a title and message.
     *
     * @param s  the title of the notification.
     * @param s1 the notification message.
     */
    protected abstract void notify(String s, String s1);

    /**
     * Sends a simple notification with a message.
     *
     * @param s the notification message.
     */
    protected abstract void notify(String s);
}
