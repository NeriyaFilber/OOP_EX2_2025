package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.management.Sessions.Session;


import java.util.ArrayList;

public class Secretary extends Person {
    public Secretary(String name, int balance, Gender gender, String dateOfBirth) {
        super(name, balance, gender, dateOfBirth);
    }

    public Client registerClient(Person p2) throws InvalidAgeException,DuplicateClientException {
    }

    public Instructor hireInstructor(Person p4, int i, ArrayList<Object> objects) {
    }

    public void registerClientToLesson(Client c1, Session s1) throws ClientNotRegisteredException, DuplicateClientException {
    }

    public void unregisterClient(Client c2) throws ClientNotRegisteredException{
    }

    public void paySalaries() {
    }

    public void printActions() {
    }

    public void notify(Session s4, String s) {
    }

    public void notify(String s, String s1) {
    }

    public void notify(String s) {
    }
}
