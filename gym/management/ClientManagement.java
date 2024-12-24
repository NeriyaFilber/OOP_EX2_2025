package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

import java.time.LocalDateTime;

/**
 * Manages client registration, unregistration, and lesson registration in the gym system.
 * This class follows the Singleton design pattern, meaning there is only one instance of
 * ClientManagement throughout the application.
 * It provides functionality to register/unregister clients, as well as ensuring that
 * client registrations for lessons meet the necessary criteria.
 */
class ClientManagement  {

    // Instance of the GymManagementSystem for interacting with the system
    private GymManagementSystem gymSystem = GymManagementSystem.getInstance();
    private NotificationManagement gymNotification = NotificationManagement.getInstance();

    /**
     * Registers a new client with the system.
     *
     * @param person The person object containing client details.
     * @return The newly registered client.
     * @throws InvalidAgeException if the client is under 18 years old.
     * @throws DuplicateClientException if the client is already registered.
     */
    protected Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if(person.getAge()<18){
            throw InvalidAgeException.getInstance();
        }
        Client tempClient = new Client(person, gymNotification);
        if (gymSystem.getClients().contains(tempClient)){
            throw new DuplicateClientException("Error: The client is already registered");
        }
        gymSystem.addClient(tempClient);
        ActionLogManager.getInstance().logAction("Registered new client: " + tempClient.getName());
        return tempClient;
    }

    /**
     * Unregisters an existing client from the system.
     *
     * @param client The client to unregister.
     * @throws ClientNotRegisteredException if the client is not registered in the system.
     */
    protected void unregisterClient(Client client) throws ClientNotRegisteredException {
        if(!gymSystem.getClients().contains(client)){
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
        // The balances remain the same even if the client re-registers or becomes an instructor/secretary
        gymSystem.removeClient(client);
        ActionLogManager.getInstance().logAction("Unregistered client: " + client.getName());
    }

    /**
     * Validates if a client can register for a given session.
     *
     * @param session The session to check registration for.
     * @param client The client attempting to register.
     * @return true if the client can register, false otherwise.
     */
    private boolean checkValidation(Session session, Client client) {
        boolean isValid = true; // Flag to track overall validity

        if (session.getDateAsLD().isBefore(LocalDateTime.now())) {
            ActionLogManager.getInstance().logAction(("Failed registration: Session is not in the future"));
            isValid = false;
        }

        if (!checkValidationForum(session.getForum(), client)) {
            isValid = false;
        }

        if (client.getBalance().get_balance() < session.getCost()) {
            ActionLogManager.getInstance().logAction(("Failed registration: Client doesn't have enough balance"));
            isValid = false;
        }

        if (session.maxNumOfParticipant() <= session.getNumOfParticipant()) {
            ActionLogManager.getInstance().logAction(("Failed registration: No available spots for session"));
            isValid = false;
        }

        return isValid;
    }

    /**
     * Validates if a client is eligible for a specific forum type session.
     *
     * @param forumType The type of forum for the session (e.g., All, Female, Male, Seniors).
     * @param client The client to validate.
     * @return true if the client is eligible for the session, false otherwise.
     */
    private boolean checkValidationForum(ForumType forumType, Client client) {
        boolean isValid = true;

        if (forumType == ForumType.Seniors) {
            if (client.getAge() < 65) {
                ActionLogManager.getInstance().logAction(("Failed registration: Client doesn't meet the age requirements for this session (Seniors)"));
                isValid = false;
            }
        } else {
            switch (forumType) {
                case All:
                    break;
                case Female:
                    if (client.getGender() != Gender.Female) {
                        ActionLogManager.getInstance().logAction(("Failed registration: Client's gender doesn't match the session's gender requirements"));
                        isValid = false;
                    }
                    break;
                case Male:
                    if (client.getGender() != Gender.Male) {
                        ActionLogManager.getInstance().logAction(("Failed registration: Client's gender doesn't match the session's gender requirements"));
                        isValid = false;
                    }
                    break;
                default:
                    ActionLogManager.getInstance().logAction(("Failed registration: Unknown forum type"));
                    isValid = false;
                    break;
            }
        }

        return isValid;
    }

    /**
     * Registers a client for a specific session.
     *
     * @param client The client to register.
     * @param session The session to register the client for.
     * @return true if the client is successfully registered for the session, false otherwise.
     * @throws ClientNotRegisteredException if the client is not registered with the gym.
     * @throws DuplicateClientException if the client is already registered for the session.
     */
    protected boolean registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        if (!gymSystem.getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        if (session.get_participant().contains(client)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }

        if (checkValidation(session, client)) {
            if (session.addParticipant(client)) {
                client.subtractBalance(session.getCost());
                ActionLogManager.getInstance().logAction("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + " for price: " + session.getCost());
            }
            return true;
        }
        return false;
    }
}
