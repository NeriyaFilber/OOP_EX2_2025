package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

/**
 * The {@code Secretary} class represents a secretary working in a gym.
 * This role is responsible for managing client registrations, hiring instructors, managing sessions,
 * and handling financial aspects such as paying salaries and overseeing gym activities through logged actions.
 */
public class Secretary implements Sender {

    /**
     * Represents the personal details of the secretary.
     */
    private Person _secretary;

    /**
     * The monthly salary of the secretary.
     */
    private int _salary;

    /**
     * The gym where the secretary is employed.
     */
    private WorkManagement _gym;

    /**
     * Constructs a new {@code Secretary} with the specified personal details and salary.
     *
     * @param person the {@code Person} object representing the secretary's details.
     * @param salary the salary of the secretary.
     */
    public Secretary(Person person, int salary) {
        this._secretary = person;
        this._salary = salary;
    }

    /**
     * Assigns the secretary to a gym.
     *
     * @param _gym the {@code Gym} object to assign to the secretary.
     */
    protected void set_gym(Gym _gym) {
        this._gym = _gym;
    }

    /**
     * Registers a new client to the gym.
     *
     * @param person the {@code Person} representing the client to register.
     * @return the registered {@code Client} object.
     * @throws InvalidAgeException      if the client's age is invalid.
     * @throws DuplicateClientException if the client is already registered.
     */
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        return _gym.registerClient(person);
    }

    /**
     * Hires a new instructor for the gym.
     *
     * @param person       the {@code Person} representing the instructor's details.
     * @param salary       the salary of the instructor.
     * @param sessionTypes the session types the instructor is qualified to teach.
     * @return the hired {@code Instructor} object.
     */
    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        return _gym.hireInstructor(person, salary, sessionTypes);
    }

    /**
     * Registers a client for a gym session.
     *
     * @param client  the {@code Client} to register.
     * @param session the {@code Session} the client will attend.
     * @throws ClientNotRegisteredException if the client is not registered in the gym.
     * @throws DuplicateClientException     if the client is already registered for the session.
     */
    public void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        _gym.registerClientToLesson(client, session);
    }

    /**
     * Unregisters a client from the gym.
     *
     * @param client the {@code Client} to unregister.
     * @throws ClientNotRegisteredException if the client is not registered in the gym.
     */
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        _gym.unregisterClient(client);
    }

    /**
     * Pays salaries to all employees in the gym.
     */
    public void paySalaries() {
        _gym.paySalaries();
    }

    /**
     * Retrieves the monthly salary of the secretary.
     *
     * @return the salary of the secretary.
     */
    public int get_salary() {
        return _salary;
    }

    /**
     * Prints the log of actions recorded in the gym.
     */
    public void printActions() {
        for (String action : _gym.get_log()) {
            System.out.println(action);
        }
    }

    /**
     * Sends a notification related to a specific session.
     *
     * @param s4 the {@code Session} associated with the notification.
     * @param s  the notification message.
     */
    @Override
    public void notify(Session s4, String s) {
        _gym.notify(s4, s);
    }

    /**
     * Sends a notification to all sessions in specific date and message.
     *
     * @param s  the date of the notification.
     * @param s1 the message of the notification.
     */
    @Override
    public void notify(String s, String s1) {
        _gym.notify(s, s1);
    }

    /**
     * Sends a simple notification with a message.
     *
     * @param s the notification message.
     */
    @Override
    public void notify(String s) {
        _gym.notify(s);
    }

    /**
     * Adds a new session to the gym system.
     *
     * @param sessionType the type of session to add.
     * @param s           the date or information about the session.
     * @param forumType   the forum type of the session.
     * @param instructor  the instructor leading the session.
     * @return the created {@code Session} object.
     * @throws InstructorNotQualifiedException if the instructor is not qualified to lead the session type.
     */
    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        return _gym.addSession(sessionType, s, forumType, instructor);
    }

    /**
     * Retrieves the personal details of the secretary.
     *
     * @return the {@code Person} representing the secretary.
     */
    public Person get_secretary() {
        return _secretary;
    }

    /**
     * Returns a string representation of the secretary's details, including personal and role information.
     *
     * @return a formatted string with the secretary's details.
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %s | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %s | Role: Secretary | Salary per Month: %d",
                _secretary.getID(),
                _secretary.getName(),
                _secretary.getGender(),
                _secretary.getDateOfBirth(),
                _secretary.getAge(),
                _secretary.getBalance(),
                _salary
        );
    }

}