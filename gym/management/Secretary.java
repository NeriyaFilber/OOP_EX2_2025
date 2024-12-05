package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionFactory;
import gym.management.Sessions.SessionType;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        _gymActions.add("A new secretary has started working at the gym: " + person.getName());
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        Client tempClient = new Client(person);//TODO check age and throw exception
        if (_gymClients.contains(tempClient)){
            throw DuplicateClientException.getInstance(false);
        }
        _gymClients.add(tempClient);
        _gymActions.add("Registered new client: " + tempClient.getName());
        return tempClient;
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        _gymActions.add("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        return new Instructor(person, salary, sessionTypes);
    }

    public void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        if(!_gymClients.contains(client)){
            throw ClientNotRegisteredSessionException.getInstance(true);
        }
        if(session.get_participant().contains(client)){
            throw DuplicateClientException.getInstance(true);
        }
        if(session.addParticipant(client)) {
            client.set_balance(-session.getCost());
            _gymBalance += session.getCost();
        }
        _gymActions.add("Registered client: " + client.getName() + " to session: " + session + " on " +   session.getDate());
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException{
        if(!_gymClients.contains(client)){
            throw ClientNotRegisteredException.getInstance(false);
        }
        _gymClients.remove(client);
        _gymActions.add("Unregistered client: " + client.getName());
    }

    public void paySalaries() {
        _secretary.set_balance(_salary);
        _gymBalance-=_salary;
        for (int i = 0; i < _gymSessions.size(); i++) {
            _gymSessions.get(i).getInstructor().set_balance(_gymSessions.get(i).getInstructor().get_salary());
            _gymBalance -= _gymSessions.get(i).getInstructor().get_salary();
        }
        _gymActions.add("Salaries have been paid to all employees");
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
        _gymActions.add("Created new session: " + sessionType + " on " + stringToLocalDateTime(s) + " with instructor: "+ instructor.getName());//TODO add date
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

    public LocalDateTime stringToLocalDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateStr, formatter);
    }
    private boolean checkValidation(ForumType forumType, Client client){
        switch (forumType){
            case Male:
                return client.getGender() == Gender.Male;
            case Female:
                return client.getGender() == Gender.Female;
            case Seniors:
                return client.getAge()
        }
    }
}
