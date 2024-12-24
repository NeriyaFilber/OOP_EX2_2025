/**
 * The Client class represents a gym client and extends the Person class.
 * It includes additional functionality for managing notifications specific to clients.
 */
package gym.customers;

import java.util.ArrayList;

/**
 * Represents a client of the gym, inheriting personal details and balance functionality from the Person class.
 * Clients have additional functionality to manage their notifications.
 */
public class Client extends Person implements Observer {

    /**
     * List of notifications associated with the client.
     */
    private ArrayList<String> _notifications = new ArrayList<>();
    private Object _sender;

    /**
     * Constructs a new Client object with the specified details.
     *
     * @param name        the name of the client
     * @param balance     the initial balance
     * @param gender      the gender of the client
     * @param dateOfBirth the date of birth in the format "dd-MM-yyyy"
     */
    public Client(String name, int balance, Gender gender, String dateOfBirth) {
        super(name, balance, gender, dateOfBirth);
    }

    /**
     * Constructs a new Client object using an existing Person object.
     *
     * @param person the Person object to copy details from
     */
    public Client(Person person, Object sender) {
        super(person);
        this._sender = sender;
    }

    /**
     * Retrieves the client's notifications as a formatted string.
     *
     * @return a string representation of the notifications, or "[]" if no notifications exist
     */
    public String getNotifications() {
        if (_notifications.isEmpty()) {
            return "[]";
        }
        StringBuilder ans = new StringBuilder("[");
        for (String not : _notifications) {
            ans.append(not);
            ans.append(", ");
        }
        ans.delete(ans.length() - 2, ans.length()); // remove the last chars ", "
        ans.append("]");
        return ans.toString();
    }

    /**
     * Compares this Client object to another for equality based on their unique IDs.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // If it's the same object
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // If the object is different or null
        }
        Client other = (Client) obj;
        // Compare the ID field of the client
        return this.getID() == other.getID(); // Assume ID is a unique identifier
    }

    /**
     * Adds a notification to the client's list of notifications.
     *
     * @param notification the notification to add
     */
    @Override
    public void addNotification(String notification, Object sender) {
        if (sender.equals(_sender)) {
            _notifications.add(notification);
        }
    }
}
