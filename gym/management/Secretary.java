package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;


import java.util.ArrayList;
import java.util.Objects;

public class Secretary {
    private Person _person;
    private int _salary;
    private ArrayList<Session> gymSessions = new ArrayList<>();
    private ArrayList<Client> gymClients = new ArrayList<>();
    private ArrayList<Instructor> gymInstructors = new ArrayList<>();

    public Secretary(Person person, int salary) {
        this._person = person;
        this._salary = salary;
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

    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor i2) throws InstructorNotQualifiedException {
    }
}
