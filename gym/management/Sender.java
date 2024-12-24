package gym.management;

import gym.management.Sessions.Session;

public interface Sender {
    /**
     * Sends a notification related to a specific session.
     *
     * @param s4 the {@code Session} associated with the notification.
     * @param s  the notification message.
     */
    public void notify(Session s4, String s);

    /**
     * Sends a notification to all sessions in specific date and message.
     *
     * @param s  the date of the notification.
     * @param s1 the message of the notification.
     */
    public void notify(String s, String s1);

    /**
     * Sends a simple notification with a message.
     *
     * @param s the notification message.
     */
    public void notify(String s);

}
