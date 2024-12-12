package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;


import java.util.ArrayList;

public class Secretary {
    private Person _secretary;
    private int _salary;
    private Gym _gym;


    public Secretary(Person person, int salary) {
        this._secretary = person;
        this._salary = salary;
    }

    protected void set_gym(Gym _gym) {
        this._gym = _gym;
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        return _gym.registerClient(person);
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        return _gym.hireInstructor(person, salary, sessionTypes);
    }

    public void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        _gym.registerClientToLesson(client,session);
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException{
        _gym.unregisterClient(client);
    }

    public void paySalaries() {
     _gym.paySalaries();
    }

    public int get_salary() {
        return _salary;
    }

    public void printActions() {
        for (String action : _gym.get_log()) {
            System.out.println(action);
        }
    }



    public void notify(Session s4, String s) {
        _gym.notify(s4,s);
    }

    public void notify(String s, String s1) {
        _gym.notify(s,s1);
    }


    public void notify(String s) {
        _gym.notify(s);
    }

    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        return _gym.addSession(sessionType,s,forumType,instructor);
    }

    public Person get_secretary() {
        return _secretary;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %s | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d | Role: Secretary | Salary per Month: %d",
                _secretary.getID(),
                _secretary.getName(),
                _secretary.getGender(),
                _secretary.getDateOfBirth(),
                _secretary.getAge(),
                _secretary.getBalance(),
                _salary
        );
    }

}
