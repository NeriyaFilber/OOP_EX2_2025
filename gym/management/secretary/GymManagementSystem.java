package gym.management.secretary;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.management.Sessions.Session;

import java.util.ArrayList;

/**
 * The {@code GymManagementSystem} class serves as the central management system for the gym,
 * handling and organizing clients, instructors, and sessions.
 * This class implements the Singleton design pattern, ensuring only one instance exists throughout the application.
 */
public class GymManagementSystem {

    /**
     * The single instance of the GymManagementSystem (Singleton).
     */
    private static GymManagementSystem instance;

    /**
     * List containing all registered clients in the gym.
     */
    private ArrayList<Client> gymClients = new ArrayList<>();

    /**
     * List containing all instructors employed in the gym.
     */
    private ArrayList<Instructor> gymInstructors = new ArrayList<>();

    /**
     * List containing all sessions available in the gym.
     */
    private ArrayList<Session> gymSessions = new ArrayList<>();

    /**
     * Manages logging actions taken within the system.
     */
    private ActionLogManager actionLogManager;

    /**
     * Private constructor to restrict instantiation of the class.
     * Ensures adherence to the Singleton design pattern.
     */
    private GymManagementSystem() {}

    /**
     * Provides access to the single instance of the {@code GymManagementSystem}.
     * If the instance does not already exist, it will be created.
     *
     * @return the single instance of the GymManagementSystem.
     */
    public static GymManagementSystem getInstance() {
        if (instance == null) {
            instance = new GymManagementSystem();
        }
        return instance;
    }

    /**
     * Adds a new client to the system's client list.
     *
     * @param client the {@code Client} to add.
     */
    public void addClient(Client client) {
        gymClients.add(client);
    }

    /**
     * Adds a new instructor to the system's instructor list.
     *
     * @param instructor the {@code Instructor} to add.
     */
    public void addInstructor(Instructor instructor) {
        gymInstructors.add(instructor);
    }

    /**
     * Adds a new session to the system's session list.
     *
     * @param session the {@code Session} to add.
     */
    public void addSession(Session session) {
        gymSessions.add(session);
    }

    /**
     * Retrieves the list of all clients in the gym.
     *
     * @return an {@code ArrayList<Client>} containing all registered clients.
     */
    public ArrayList<Client> getClients() {
        return gymClients;
    }

    /**
     * Retrieves the list of all sessions in the gym.
     *
     * @return an {@code ArrayList<Session>} containing all available sessions.
     */
    public ArrayList<Session> getSessions() {
        return gymSessions;
    }

    /**
     * Removes a client from the system's client list.
     *
     * @param client the {@code Client} to remove.
     */
    public void removeClient(Client client) {
        gymClients.remove(client);
    }

    /**
     * Retrieves the list of all instructors in the gym.
     *
     * @return an {@code ArrayList<Instructor>} containing all instructors.
     */
    public ArrayList<Instructor> getGymInstructors() {
        return gymInstructors;
    }

    /**
     * Removes an instructor from the system's instructor list.
     *
     * @param instructor the {@code Instructor} to remove.
     */
    public void removeInstructor(Instructor instructor) {
        gymInstructors.remove(instructor);
    }

    /**
     * Removes a session from the system's session list.
     *
     * @param session the {@code Session} to remove.
     */
    public void removeSession(Session session) {
        gymSessions.remove(session);
    }
}