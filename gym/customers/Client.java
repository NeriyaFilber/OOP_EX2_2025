package gym.customers;

import gym.management.Gender;
import gym.management.Person;

import java.util.ArrayList;
import java.util.Objects;

public class Client extends Person {
    private ArrayList<String> _notifications = new ArrayList<>();

    public Client(String name, int balance, Gender gender, String dateOfBirth) {
        super(name, balance, gender, dateOfBirth);
    }

    public Client(Person p) {
        super(p);
    }

    public String getName() {
        return super.getName();
    }

    public String getNotifications() {
        if (_notifications.isEmpty()) {
            return "[]";
        }
        StringBuilder ans = new StringBuilder("[");
        for (String not : _notifications) {
            ans.append(not);
            ans.append(",");
        }
        ans.deleteCharAt(ans.length() - 1); //remove the last char ","
        ans.append("]");
        return ans.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return this.getID() == ((Client) o).getID();
    }
}
