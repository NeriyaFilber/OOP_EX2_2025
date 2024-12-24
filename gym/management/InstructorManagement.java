package gym.management;

import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

/**
 * The {@code InstructorManagement} class handles the hiring and registration of instructors in the gym.
 * It provides functionality to create new instructors and add them to the gym's system.
 */
class InstructorManagement {

    /**
     * Hires a new instructor for the gym.
     * Creates an {@code Instructor} object with the given personal details, salary, and session types,
     * and adds the instructor to the system.
     *
     * @param person       the {@code Person} object containing the instructor's personal details.
     * @param salary       the instructor's salary per hour.
     * @param sessionTypes an {@code ArrayList<SessionType>} containing the session types the instructor can teach.
     * @return the newly created {@code Instructor} object.
     */
    protected Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> sessionTypes) {
        GymManagementSystem gymSystem = GymManagementSystem.getInstance();

        Instructor newInstructor = new Instructor(person, salary, sessionTypes);

        gymSystem.addInstructor(newInstructor);
        ActionLogManager.getInstance().logAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);

        return newInstructor;
    }

}