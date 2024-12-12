package gym.management.secretary;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.management.Sessions.Session;

import java.util.ArrayList;

public class GymManagementSystem {
    private static GymManagementSystem instance;

    private ArrayList<Client> gymClients = new ArrayList<>();
    private ArrayList<Instructor> gymInstructors = new ArrayList<>();
    private ArrayList<Session> gymSessions = new ArrayList<>();

    private ActionLogManager actionLogManager;

    private GymManagementSystem() {}

    public static GymManagementSystem getInstance() {
        if (instance == null) {
            instance = new GymManagementSystem();
        }
        return instance;
    }



    public void addClient(Client client) {
        gymClients.add(client);
    }

    public void addInstructor(Instructor instructor) {
        gymInstructors.add(instructor);
    }

    public void addSession(Session session) {
        gymSessions.add(session);

    }
    // Getters
    public ArrayList<Client> getClients() {
        return gymClients;
    }

    public ArrayList<Session> getSessions() {
        return gymSessions;
    }

    public void removeClient(Client client) {
        gymClients.remove(client);
    }

    public ArrayList<Instructor> getGymInstructors() {
        return gymInstructors;
    }

    public void removeInstructor(Instructor instructor) {
        gymInstructors.remove(instructor);
    }

    // פונקציה להסרת שיעור מהרשימה
    public void removeSession(Session session) {
        gymSessions.remove(session);
    }

    }


