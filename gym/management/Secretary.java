package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionFactory;
import gym.management.Sessions.SessionType;



import java.time.LocalDateTime;
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

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if(person.getAge()<18){
            throw InvalidAgeException.getInstance();
        }
        Client tempClient = new Client(person);
        if (_gymClients.contains(tempClient)){
            throw DuplicateClientException.getInstance(false);
        }

        _gymClients.add(tempClient);
        _gymActions.add("Registered new client: " + tempClient.getName());
        return tempClient;
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        _gymActions.add("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        if(_gymClients.contains(new Client(person))){
            Instructor newInstructorP = new Instructor(person,sessionTypes,salary);
            _gymInstructors.add(newInstructorP);
            return newInstructorP;
        }
        else {
            Instructor newInstructor = new Instructor(person, salary, sessionTypes);
            _gymInstructors.add(newInstructor);
            return newInstructor;
        }
    }

    public void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        if (!_gymClients.contains(client)) {
            throw ClientNotRegisteredSessionException.getInstance(true);
        }
        if (session.get_participant().contains(client)) {
            throw DuplicateClientException.getInstance(true);
        }

        if (checkValidation(session, client)) {
            if (session.addParticipant(client)) {
                client.set_balance(-session.getCost());
                _gymBalance += session.getCost();
                _gymActions.add("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + " for price: " + session.getCost());
            }
        }
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException{
        if(!_gymClients.contains(client)){
            throw ClientNotRegisteredException.getInstance(false);
        }
        _gymClients.remove(client);
        for (Session session: _gymSessions){//TODO check if client is unregister I need to unregister him from all sessions?
            if(session.get_participant().contains(client)){
                session.removeParticipant(client);
            }
        }
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
        for (int i = 0; i < _gymActions.size(); i++) {
            System.out.println(_gymActions.get(i));
        }
    }

    public void notify(Session s4, String s) {
        String registered_session = new String("A message was sent to everyone registered for session ");
        _gymActions.add(registered_session + " on " + s4.getType()+ " on " + s4.getDate() + " : "  + s );
    }

    public void notify(String s, String s1) {
        String registered_session = new String("A message was sent to everyone registered for a session on ");
        _gymActions.add(registered_session + s + s1 );

    }

    public void notify(String s) {
        String all = new String("A message was sent to all gym clients: ");
        _gymActions.add(all + s);


    }

    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getCertifiedClasses().contains(sessionType)){
            throw InstructorNotQualifiedException.getInstance();
        }
        Session session = SessionFactory.createSession(sessionType,s,forumType,instructor);
        _gymSessions.add(session);
        _gymActions.add("Created new session: " + sessionType + " on " + session.getDate() + " with instructor: "+ instructor.getName());//TODO add date
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

    void logAction(String action) {
        _gymActions.add(action);
    }

    private boolean checkValidation(Session session, Client client){
        if (session.getDateAsLD().isBefore(LocalDateTime.now())) {
            _gymActions.add("Failed registration: Session is not in the future");
            return false;
        }
        if (client.getBalance()< session.getCost()){
            _gymActions.add("Failed registration: Client doesn't have enough balance");
            return false;
        }
        if (session.maxNumOfParticipant() <= session.getNumOfParticipant()){
            _gymActions.add("Failed registration: No available spots for session");
            return false;
        }
        return checkValidationForum(session.getForum(), client);
    }

    private boolean checkValidationForum(ForumType forumType, Client client) {
        if (forumType == ForumType.Seniors) {
            if (client.getAge() >= 65) {
                return true;
            }
            _gymActions.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            return false;
        }
        switch (forumType) {
            case All:
                return true;
            case Female:
                if (client.getGender() == Gender.Female) {
                    return true;
                }
            case Male:
                if (client.getGender() == Gender.Male) {
                    return true;
                }
        }
        _gymActions.add("Failed registration: Client's gender doesn't match the session's gender requirements");
        return false;
    }

    public Person get_secretary() {
        return _secretary;
    }
    public String get_role() {
        return new String("Secretary");

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Clients Data:\n");
        for (Client client : _gymClients) {
            sb.append(client.toString()).append("\n");
        }
        sb.append("\nEmployees Data:\n");
        for (Instructor employee : _gymInstructors) {
            sb.append(employee.toString()).append("\n");
        }
        sb.append("\nSessions Data:\n");
        for (Session session : _gymSessions) {
            sb.append(session.toString()).append("\n");
        }
        return sb.toString();
    }
}
