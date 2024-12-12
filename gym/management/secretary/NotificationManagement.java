package gym.management.secretary;

import gym.customers.Client;
import gym.management.Sessions.Session;

import java.time.format.DateTimeFormatter;

public class NotificationManagement {

    private ClientManagement clientManagement;
    private SessionManagement sessionManagement;

    GymManagementSystem gymSystem = GymManagementSystem.getInstance();


    public NotificationManagement(ClientManagement clientManagement, SessionManagement sessionManagement) {
        this.clientManagement = clientManagement;
        this.sessionManagement = sessionManagement;
    }

    public void notify(Session s4, String s) {

        String registered_session = new String("A message was sent to everyone registered for session ");
        String message = registered_session + s4.getType()+ " on " + s4.getDate() + " : "  + s;
        ActionLogManager.getInstance().logAction(message);
        for (Client participant : s4.get_Client()) {
            participant.addNotification(s);
        }
    }

    public void notify(String s, String s1) {
        String registered_session = "A message was sent to everyone registered for a session on ";
        String message = registered_session + s + " : " + s1; //TODO correct s to format YYYY-MM-DD
        ActionLogManager.getInstance().logAction(message);


        for (Session session :gymSystem.getSessions() ) {
            if (session.sessionDateYear().equals(s)) {
                for (Client participant : session.get_Client()) {
                    participant.addNotification(s1);
                }
            }
        }
    }


    public void notify(String s) {

        String all = new String("A message was sent to all gym clients: ");
        ActionLogManager.getInstance().logAction(all + s);
        for (Client participant : gymSystem.getClients()) {
            participant.addNotification(s);
        }
    }


}
