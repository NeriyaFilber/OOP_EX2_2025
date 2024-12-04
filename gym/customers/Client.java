package gym.customers;

import gym.management.Gender;
import gym.management.Person;

public class Client extends Person {

    public Client(String name, int balance, Gender gender, String dateOfBirth) {
        super(name, balance, gender, dateOfBirth);
    }

    public String getName() {
        return super.getName();
    }

    public String getNotifications() {
        return "";
    }
}
