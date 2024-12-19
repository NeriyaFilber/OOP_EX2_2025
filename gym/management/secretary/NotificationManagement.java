package gym.management.secretary;

import gym.customers.Client;
import gym.management.Sessions.Session;

import java.time.LocalDate;
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
        String message = registered_session + formatDate(s) + " : " + s1;
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

    private String formatDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate parsedDate = LocalDate.parse(date, inputFormatter);
        return parsedDate.format(outputFormatter);
    }

}
