package gym.management;


import gym.Balance;
import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;
import gym.management.secretary.*;

import java.util.ArrayList;

public class Gym {
    private static Gym gym;
    private static Secretary _secretary;
    private static Balance _balance = new Balance(0);
    private String _nameGym;

    GymManagementSystem gymSystem = GymManagementSystem.getInstance();
    private ClientManagement clientManagement;
    private InstructorManagement instructorManagement;
    private SessionManagement sessionManagement;
    private NotificationManagement notificationManagement;
    private ActionLogManager _log;


    private Gym() {
        this.clientManagement = new ClientManagement();
        this.instructorManagement = new InstructorManagement();
        this.sessionManagement = new SessionManagement();
        this.notificationManagement = new NotificationManagement(clientManagement, sessionManagement);
        this._log = ActionLogManager.getInstance();
    }

    public static Gym getInstance() {
        if (gym == null) {
            gym = new Gym();
        }
        return gym;
    }

    public void setName(String crossFit) {
        this._nameGym = crossFit;
    }

    public void setSecretary(Person p1, int salary) {
        if (_secretary != null) {
            _secretary.set_gym(null);
        }
        _secretary = new Secretary(p1, salary);
        _secretary.set_gym(this);

        ActionLogManager.getInstance().logAction("A new secretary has started working at the gym: " + p1.getName());
    }



    public Secretary getSecretary() {
        return _secretary;

    }

    @Override
    public String toString() {
        return "Gym Name: " + _nameGym + "\n" +
                "Gym Secretary: "+ _secretary.toString() + "\n" + "Gym Balance: " + _balance + "\n" + gymInfo();
    }

    protected Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        return clientManagement.registerClient(person);
    }

    protected Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        return instructorManagement.hireInstructor(person, salary, sessionTypes);
    }

    protected void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        if (clientManagement.registerClientToLesson(client, session)){
            _balance.add( session.getCost());
        }
    }

    protected void unregisterClient(Client client) throws ClientNotRegisteredException {
        clientManagement.unregisterClient(client);
    }

    protected void paySalaries() {
        for (Session session: gymSystem.getSessions()){
            _balance.subtractBalance(session.getInstructor().get_salary());
            session.getInstructor().addBalance(session.get_instructor().get_salary());
        }
        _balance.subtractBalance( _secretary.get_salary());
        _secretary.get_secretary().set_balance(_secretary.get_salary() + _secretary.get_secretary().getBalance().get_balance());
        ActionLogManager.getInstance().logAction("Salaries have been paid to all employees");
    }

    protected ArrayList<String> get_log() {
        return _log.getActions();
    }

    protected Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        return sessionManagement.addSession(sessionType, s, forumType, instructor);
    }

    protected void notify(Session s4, String s) {
        notificationManagement.notify(s4, s);
    }
    protected void notify(String s, String s1) {
        notificationManagement.notify(s,s1);
    }
    protected void notify(String s) {
        notificationManagement.notify(s);
    }
    private String gymInfo() {
        StringBuilder sb = new StringBuilder("\nClients Data:\n");
        for (Client client : gymSystem.getClients()) {
            sb.append(client.toString()).append("\n");
        }
        sb.append("\nEmployees Data:\n");
        for (Instructor instructor : gymSystem.getGymInstructors()) {
            sb.append(instructor.toString()).append("\n"); // לעדכן את ההדפסות לפי גם מזכירה
        }
        sb.append(_secretary.toString()).append("\n");
        sb.append("\nSessions Data:\n");
        for (int i = 0; i < gymSystem.getSessions().size(); i++) {
            sb.append(gymSystem.getSessions().get(i).toString());
            if (i < gymSystem.getSessions().size() - 1) {
                sb.append("\n"); //
            }
        }
        return sb.toString();
    }
}
