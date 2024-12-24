package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;

import java.util.ArrayList;

/**
 * The {@code Gym} class represents the main management system of a gym.
 * It implements various management modules, including client, instructor, session, and notification management.
 * The class employs the Singleton design pattern to ensure that only one instance of the gym exists within the application.
 */
public class Gym extends WorkManagement{
    /**
     * The singleton instance of the {@code Gym}.
     */
    private static Gym gym;

    /**
     * The secretary working at the gym.
     */
    private static Secretary _secretary;

    /**
     * The financial balance of the gym.
     */
    private static Balance _balance = new Balance(0);

    /**
     * The name of the gym.
     */
    private String _nameGym;

    /**
     * Instance of {@code GymManagementSystem}.
     */
    GymManagementSystem gymSystem = GymManagementSystem.getInstance();

    /**
     * Manages client-related operations in the gym.
     */
    private ClientManagement clientManagement;

    /**
     * Manages instructor-related operations in the gym.
     */
    private InstructorManagement instructorManagement;

    /**
     * Manages session-related operations in the gym.
     */
    private SessionManagement sessionManagement;

    /**
     * Manages notification-related operations in the gym.
     */
    private NotificationManagement notificationManagement;

    /**
     * Logs gym-related actions.
     */
    private ActionLogManager _log;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Serves the Singleton design pattern and initializes relevant management modules.
     */
    private Gym() {
        this.clientManagement = new ClientManagement();
        this.instructorManagement = new InstructorManagement();
        this.sessionManagement = new SessionManagement();
        this.notificationManagement = NotificationManagement.getInstance();
        this._log = ActionLogManager.getInstance();
    }

    /**
     * Retrieves the singleton instance of the {@code Gym}.
     * Creates the instance if it does not already exist.
     *
     * @return the single {@code Gym} instance.
     */
    public static Gym getInstance() {
        if (gym == null) {
            gym = new Gym();
        }
        return gym;
    }

    /**
     * Sets the name of the gym.
     *
     * @param crossFit the name of the gym to set.
     */
    public void setName(String crossFit) {
        this._nameGym = crossFit;
    }

    /**
     * Assigns a secretary to the gym. If a secretary already exists,
     * the previous one will be removed from the gym.
     *
     * @param p1     the {@code Person} representing the new secretary.
     * @param salary the salary of the new secretary.
     */
    public void setSecretary(Person p1, int salary) {
        if (_secretary != null) {
            _secretary.set_gym(null);
        }
        _secretary = new Secretary(p1, salary);
        _secretary.set_gym(this);

        ActionLogManager.getInstance().logAction("A new secretary has started working at the gym: " + p1.getName());
    }

    /**
     * Retrieves the secretary currently working at the gym.
     *
     * @return the {@code Secretary} object.
     */
    public Secretary getSecretary() {
        return _secretary;
    }

    /**
     * Returns a string representation of the gym, including its name, secretary, balance, and overall information.
     *
     * @return a formatted string with gym details.
     */
    @Override
    public String toString() {
        return "Gym Name: " + _nameGym + "\n" +
                "Gym Secretary: " + _secretary.toString() + "\n" + "Gym Balance: " + _balance + "\n" + gymInfo();
    }

    /**
     * Registers a new client in the gym.
     *
     * @param person the {@code Person} object representing the client.
     * @return the newly registered {@code Client} object.
     * @throws InvalidAgeException      if the client's age is invalid.
     * @throws DuplicateClientException if the client already exists in the system.
     */

    protected Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        return clientManagement.registerClient(person);
    }

    /**
     * Hires a new instructor for the gym.
     *
     * @param person       the {@code Person} representing the instructor.
     * @param salary       the salary of the instructor.
     * @param sessionTypes the session types the instructor is qualified to teach.
     * @return the newly hired {@code Instructor} object.
     */
    protected Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        return instructorManagement.hireInstructor(person, salary, sessionTypes);
    }

    /**
     * Registers a client to a gym session and updates the gym balance.
     *
     * @param client  the {@code Client} to register.
     * @param session the {@code Session} the client will attend.
     * @throws ClientNotRegisteredException if the client is not registered in the system.
     * @throws DuplicateClientException     if the client is already registered for the session.
     */
    protected void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        if (clientManagement.registerClientToLesson(client, session)) {
            _balance.add(session.getCost());
        }
    }

    /**
     * Unregisters a client from the gym system.
     *
     * @param client the {@code Client} to unregister.
     * @throws ClientNotRegisteredException if the client is not registered in the system.
     */
    protected void unregisterClient(Client client) throws ClientNotRegisteredException {
        clientManagement.unregisterClient(client);
    }

    /**
     * Pays salaries to all employees, including instructors and the secretary,
     * and deducts the corresponding amounts from the gym's balance.
     * Logs the action upon completion.
     */
    protected void paySalaries() {
        for (Session session : gymSystem.getSessions()) {
            _balance.subtractBalance(session.getInstructor().get_salary());
            session.getInstructor().addBalance(session.get_instructor().get_salary());
        }
        _balance.subtractBalance(_secretary.get_salary());
        _secretary.get_secretary().set_balance(_secretary.get_salary() + _secretary.get_secretary().getBalance().get_balance());
        ActionLogManager.getInstance().logAction("Salaries have been paid to all employees");
    }

    /**
     * Retrieves the log of all recorded actions in the gym.
     *
     * @return an {@code ArrayList<String>} of logged actions.
     */
    protected ArrayList<String> get_log() {
        return _log.getActions();
    }

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
    protected Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        return sessionManagement.addSession(sessionType, s, forumType, instructor);
    }

    /**
     * Sends a notification related to a session.
     *
     * @param s4 the {@code Session} associated with the notification.
     * @param s  the notification message.
     */
    protected void notify(Session s4, String s) {
        notificationManagement.notify(s4, s);
    }

    /**
     * Sends a notification with a title and message.
     *
     * @param s  the title of the notification.
     * @param s1 the notification message.
     */
    protected void notify(String s, String s1) {
        notificationManagement.notify(s, s1);
    }

    /**
     * Sends a simple notification with a message.
     *
     * @param s the notification message.
     */
    protected void notify(String s) {
        notificationManagement.notify(s);
    }

    /**
     * Generates information about the gym's clients, employees, sessions, and other data.
     *
     * @return a detailed string representation of the gym's data.
     */
    private String gymInfo() {
        StringBuilder sb = new StringBuilder("\nClients Data:\n");
        for (Client client : gymSystem.getClients()) {
            sb.append(client.toString()).append("\n");
        }
        sb.append("\nEmployees Data:\n");
        for (Instructor instructor : gymSystem.getGymInstructors()) {
            sb.append(instructor.toString()).append("\n");
        }
        sb.append(_secretary.toString()).append("\n");
        sb.append("\nSessions Data:\n");
        for (int i = 0; i < gymSystem.getSessions().size(); i++) {
            sb.append(gymSystem.getSessions().get(i).toString());
            if (i < gymSystem.getSessions().size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}