package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionFactory;
import gym.management.Sessions.SessionType;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Secretary {
    private Person _secretary;
    private int _gymBalance = 0;
    private int _salary;
    private ArrayList<Session> _gymSessions = new ArrayList<>();
    private ArrayList<Client> _gymClients = new ArrayList<>();
    private ArrayList<Instructor> _gymInstructors = new ArrayList<>();
   private ArrayList<String> _gymActions =new ArrayList<>();

    private ArrayList<Person> _getNotification =new ArrayList<>();

    private ArrayList<Person> _gymAllWorker =new ArrayList<>();





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
            _gymAllWorker.add(newInstructorP);

            return newInstructorP;
        }
        else {
            Instructor newInstructor = new Instructor(person, salary, sessionTypes);
            _gymInstructors.add(newInstructor);
            _gymAllWorker.add(newInstructor);
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
                client.set_balance(client.getBalance()-session.getCost());
                _gymBalance += session.getCost();
                _gymActions.add("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDate() + " for price: " + session.getCost());
            }
        }
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException{
        if(!_gymClients.contains(client)){
            throw ClientNotRegisteredException.getInstance(false);
        }
        // נשים לב שהיתרות נשארות אותו דבר במידה ונרשם שוב (או ניהיה מדריך.מזכירה)
        _gymClients.remove(client);
        _gymActions.add("Unregistered client: " + client.getName());
    }

    public void paySalaries() {
        _secretary.set_balance(_secretary.getBalance()+_salary);
        _gymBalance-=_salary;
        for (int i = 0; i < _gymSessions.size(); i++) {
            _gymSessions.get(i).getInstructor().set_balance(_gymSessions.get(i).getInstructor().getBalance()+_gymSessions.get(i).getInstructor().get_salary());
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
        String message = registered_session + " on " + s4.getType()+ " on " + s4.getDate() + " : "  + s;
        _gymActions.add(message);
        for (Client participant : s4.get_Client()) {
            participant.addNotification(s); 
        }
    }

    public void notify(String s, String s1) {
        String registered_session = "A message was sent to everyone registered for a session on ";
        String message = registered_session + s + " : " + s1;

        _gymActions.add(message);


        for (Session session : _gymSessions) {
            if (session.sessionDateYear().equals(s)) {
                for (Client participant : session.get_Client()) {
                    participant.addNotification(s1);
                }
            }
        }
    }


    public void notify(String s) {
        String all = new String("A message was sent to all gym clients: ");
        _gymActions.add(all + s);
        for (Client participant : _gymClients) {
            participant.addNotification(s);
        }


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

        this._gymAllWorker = new ArrayList<>(secretary._gymAllWorker);
    }

    public void clearSecretary(){
        this._gymActions = null;
        this._gymInstructors =null;
        this._gymSessions = null;
        this._gymBalance = 0 ;
        this._gymClients = null;

        this._gymAllWorker = null;
    }

    void logAction(String action) {
        _gymActions.add(action);
    }



    public ArrayList<Person> get_getNotification() {
        return _getNotification;
    }
    private boolean checkValidation(Session session, Client client) {
        boolean isValid = true; // Flag to track overall validity


        if (session.getDateAsLD().isBefore(LocalDateTime.now())) {
            _gymActions.add("Failed registration: Session is not in the future");
            isValid = false;
        }

        if (!checkValidationForum(session.getForum(), client)) {
            isValid = false;
        }


        if (client.getBalance() < session.getCost()) {
            _gymActions.add("Failed registration: Client doesn't have enough balance");
            isValid = false;
        }


        if (session.maxNumOfParticipant() <= session.getNumOfParticipant()) {
            _gymActions.add("Failed registration: No available spots for session");
            isValid = false;
        }



        return isValid;
    }

    private boolean checkValidationForum(ForumType forumType, Client client) {
        boolean isValid = true;

        if (forumType == ForumType.Seniors) {
            if (client.getAge() < 65) {
                _gymActions.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                isValid = false;
            }
        } else {
            switch (forumType) {
                case All:
                    break;
                case Female:
                    if (client.getGender() != Gender.Female) {
                        _gymActions.add("Failed registration: Client's gender doesn't match the session's gender requirements");
                        isValid = false;
                    }
                    break;
                case Male:
                    if (client.getGender() != Gender.Male) {
                        _gymActions.add("Failed registration: Client's gender doesn't match the session's gender requirements");
                        isValid = false;
                    }
                    break;
                default:
                    _gymActions.add("Failed registration: Unknown forum type");
                    isValid = false;
                    break;
            }
        }

        return isValid;
    }


//        private boolean checkValidation(Session session, Client client){
//        if (session.getDateAsLD().isBefore(LocalDateTime.now())) {
//            _gymActions.add("Failed registration: Session is not in the future");
//            return false;
//        }
//        if (client.getBalance()< session.getCost()){
//            _gymActions.add("Failed registration: Client doesn't have enough balance");
//            return false;
//        }
//        if (session.maxNumOfParticipant() <= session.getNumOfParticipant()){
//            _gymActions.add("Failed registration: No available spots for session");
//            return false;
//        }
//        return checkValidationForum(session.getForum(), client);
//    }
//
//
//
//
//
//    private boolean checkValidationForum(ForumType forumType, Client client) {
//        if (forumType == ForumType.Seniors) {
//            if (client.getAge() >= 65) {
//                return true;
//            }
//            _gymActions.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
//            return false;
//        }
//        switch (forumType) {
//            case All:
//                return true;
//            case Female:
//                if (client.getGender() == Gender.Female) {
//                    return true;
//                }
//            case Male:
//                if (client.getGender() == Gender.Male) {
//                    return true;
//                }
//        }
//        _gymActions.add("Failed registration: Client's gender doesn't match the session's gender requirements");
//        return false;
//    }

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
        for (Person worker : _gymAllWorker) {
            sb.append(worker.toString()).append("\n"); // לעדכן את ההדפסות לפי גם מזכירה
        }
        sb.append("\nSessions Data:\n");
        for (Session session : _gymSessions) {
            sb.append(session.toString()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Client> getGymClients() {
        return _gymClients;
    }

    public int get_gymBalance() {
        return _gymBalance;
    }

    void get_allWork(Person worker) {
        _gymAllWorker.add(worker);
    }






}
