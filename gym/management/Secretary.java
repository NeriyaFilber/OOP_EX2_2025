package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionFactory;
import gym.management.Sessions.SessionType;


import java.util.ArrayList;

public class Secretary {
    private Person _secretary;
    private int _gymBalance = 0;
    private int _salary;
    private ArrayList<Session> _gymSessions = new ArrayList<>();
    private ArrayList<Client> _gymClients = new ArrayList<>();
    private ArrayList<Instructor> _gymInstructors = new ArrayList<>();
    private ArrayList<String> _gymActions =new ArrayList<>();

    public Secretary(Person person, int salary) {
        this._secretary = person;
        this._salary = salary;
    }

    public Client registerClient(Person person) throws InvalidAgeException,DuplicateClientException {
        Client tempClient = new Client(person);//TODO check age and throw exception
        if (_gymClients.contains(tempClient)){
            throw DuplicateClientException.getInstance();
        }
        _gymClients.add(tempClient);
        return tempClient;
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        return new Instructor(person, salary, sessionTypes);
    }

    public void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        if(!_gymClients.contains(client)){
            throw ClientNotRegisteredSessionException.getInstance();
        }
        if(session.get_participant().contains(client)){
            throw DuplicateClientSessionException.getInstance();
        }
        if(session.addParticipant(client)) {
            client.set_balance(-session.getCost());
            _gymBalance += session.getCost();
        }
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException{
        if(!_gymClients.contains(client)){
            throw ClientNotRegisteredException.getInstance();
        }
        _gymClients.remove(client);
    }

    public void paySalaries() {
        _secretary.set_balance(_salary);
        _gymBalance-=_salary;
        for (int i = 0; i < _gymSessions.size(); i++) {
            _gymSessions.get(i).getInstructor().set_balance(_gymSessions.get(i).getInstructor().get_salary());
            _gymBalance -= _gymSessions.get(i).getInstructor().get_salary();
        }
    }

    public void printActions() {
    }

    public void notify(Session s4, String s) {
    }

    public void notify(String s, String s1) {
    }

    public void notify(String s) {
    }

    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getCertifiedClasses().contains(sessionType)){
            throw InstructorNotQualifiedException.getInstance();
        }
        Session session = SessionFactory.createSession(sessionType,s,forumType,instructor);
        _gymSessions.add(session);
        return session;
    }
    public void copySecretary(Secretary secretary){
        this._gymClients = new ArrayList<>(secretary._gymClients);
        this._gymBalance = secretary._gymBalance;
        this._gymSessions = new ArrayList<>(secretary._gymSessions);
        this._gymInstructors = new ArrayList<>(secretary._gymInstructors);
        this._gymActions = new ArrayList<>(secretary._gymActions);
    }

    public void clearSecretary(){
        this._gymActions = null;
        this._gymInstructors =null;
        this._gymSessions = null;
        this._gymBalance = 0;
        this._gymClients = null;
    }
}
