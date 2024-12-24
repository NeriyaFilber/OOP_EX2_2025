package gym.management;

import gym.customers.Client;
import gym.management.Sessions.Session;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * NotificationManagement is responsible for managing notifications sent to gym clients and sessions.
 * This class uses the Singleton design pattern to ensure that only one instance is created.
 * It logs all notification-related actions and interacts with the GymManagementSystem to access clients and sessions.
 */
class NotificationManagement {

    /**
     * Singleton instance of the NotificationManagement class.
     */
    private static NotificationManagement nm = new NotificationManagement();

    /**
     * Reference to the GymManagementSystem singleton instance.
     */
    private GymManagementSystem gymSystem = GymManagementSystem.getInstance();

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private NotificationManagement() {
    }

    /**
     * Sends a notification to all participants of a specific session.
     * Logs the notification action and updates the clients' notification lists.
     *
     * @param session The session to notify participants about.
     * @param message The notification message.
     */
    protected void notify(Session session, String message) {
        String registeredSession = "A message was sent to everyone registered for session ";
        String fullMessage = registeredSession + session.getType() + " on " + session.getDate() + " : " + message;
        ActionLogManager.getInstance().logAction(fullMessage);

        for (Client participant : session.get_Client()) {
            participant.addNotification(message, this);
        }
    }

    /**
     * Sends a notification to all participants of sessions occurring on a specific date.
     * Logs the notification action and updates the clients' notification lists.
     *
     * @param date    The date of the sessions (in "dd-MM-yyyy" format).
     * @param message The notification message.
     */
    protected void notify(String date, String message) {
        String registeredSession = "A message was sent to everyone registered for a session on ";
        String formattedDate = formatDate(date);
        String fullMessage = registeredSession + formattedDate + " : " + message;
        ActionLogManager.getInstance().logAction(fullMessage);

        for (Session session : gymSystem.getSessions()) {
            if (session.sessionDateYear().equals(date)) {
                for (Client participant : session.get_Client()) {
                    participant.addNotification(message, this);
                }
            }
        }
    }

    /**
     * Sends a notification to all clients registered in the gym system.
     * Logs the notification action and updates the clients' notification lists.
     *
     * @param message The notification message.
     */
    protected void notify(String message) {
        String allClientsMessage = "A message was sent to all gym clients: ";
        ActionLogManager.getInstance().logAction(allClientsMessage + message);

        for (Client participant : gymSystem.getClients()) {
            participant.addNotification(message, this);
        }
    }

    /**
     * Formats a date string from "dd-MM-yyyy" to "yyyy-MM-dd" format.
     *
     * @param date The date string in "dd-MM-yyyy" format.
     * @return The formatted date string in "yyyy-MM-dd" format.
     */
    private String formatDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate parsedDate = LocalDate.parse(date, inputFormatter);
        return parsedDate.format(outputFormatter);
    }

    /**
     * Provides access to the singleton instance of NotificationManagement.
     *
     * @return The singleton instance of NotificationManagement.
     */
    protected static NotificationManagement getInstance() {
        return nm;
    }
}
