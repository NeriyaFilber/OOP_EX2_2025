package gym.management.secretary;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class InstructorManagement {


    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        GymManagementSystem gymSystem = GymManagementSystem.getInstance();

        Instructor newInstructor = new Instructor(person, salary, sessionTypes);

        gymSystem.addInstructor(newInstructor);
        ActionLogManager.getInstance().logAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);

        return newInstructor;
    }

}
