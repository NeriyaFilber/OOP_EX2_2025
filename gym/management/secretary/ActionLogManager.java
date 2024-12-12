package gym.management.secretary;

import java.util.ArrayList;


public class ActionLogManager {
    private static ActionLogManager instance;
    private ArrayList<String> actions;

    private ActionLogManager() {
        actions = new ArrayList<>();
    }

    public static ActionLogManager getInstance() {
        if (instance == null) {
            instance = new ActionLogManager();
        }
        return instance;
    }

    public void logAction(String action) {
        actions.add(action);
    }

    public ArrayList<String> getActions() {
        return new ArrayList<>(actions);
    }

}
