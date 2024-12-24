package gym.management;

import java.util.ArrayList;

/**
 * This class manages the action logs within the gym management system.
 * It ensures that actions performed by users are logged and can be retrieved later.
 * <p>
 * The class follows the Singleton design pattern to ensure that only one instance
 * of the action log manager exists throughout the application.
 * </p>
 */
class ActionLogManager {

    // Static instance for the Singleton design pattern
    private static ActionLogManager instance;

    // List to hold the actions
    private ArrayList<String> actions;

    /**
     * Private constructor to initialize the actions list.
     * This constructor is private to prevent direct instantiation.
     */
    private ActionLogManager() {
        actions = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the {@code ActionLogManager}.
     * If the instance does not exist, it is created; otherwise, the existing instance is returned.
     *
     * @return the singleton instance of the ActionLogManager
     */
    protected static ActionLogManager getInstance() {
        if (instance == null) {
            instance = new ActionLogManager();
        }
        return instance;
    }

    /**
     * Logs a new action by adding it to the action list.
     *
     * @param action the action to be logged
     */
    protected void logAction(String action) {
        actions.add(action);
    }

    /**
     * Returns a copy of the list of logged actions.
     * The returned list is a new instance, ensuring that the original list is not modified directly.
     *
     * @return a list of all logged actions
     */
    protected ArrayList<String> getActions() {
        return new ArrayList<>(actions);
    }
}
