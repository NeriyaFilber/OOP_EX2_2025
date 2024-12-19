package gym.management.secretary;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

import java.time.LocalDateTime;

public class ClientManagement  {

    GymManagementSystem gymSystem = GymManagementSystem.getInstance();

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if(person.getAge()<18){
            throw InvalidAgeException.getInstance();
        }
        Client tempClient = new Client(person);
        if (gymSystem.getClients().contains(tempClient)){
            throw new DuplicateClientException("Error: The client is already registered");
        }
        gymSystem.addClient(tempClient);
        ActionLogManager.getInstance().logAction("Registered new client: " + tempClient.getName());
        return tempClient;
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if(!gymSystem.getClients().contains(client)){
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
        // נשים לב שהיתרות נשארות אותו דבר במידה ונרשם שוב (או ניהיה מדריך.מזכירה)
        gymSystem.removeClient(client);
        ActionLogManager.getInstance().logAction("Unregistered client: " + client.getName());
    }
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

    public boolean registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        if (!gymSystem.getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        if (session.get_participant().contains(client)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }

        if (checkValidation(session, client)) {
            if (session.addParticipant(client)) {
                client.subtractBalance(session.getCost());
//                gymSystem.addToGymBalance(session.getCost());
                ActionLogManager.getInstance().logAction("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + " for price: " + session.getCost());
            }
            return true;
        }
        return false;
    }



}
