/**
 * Represents an instructor in the gym, extending the Person class.
 * Instructors have a salary per hour and a list of certified classes they can teach.
 */
package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;

/**
 * Represents an instructor with additional properties such as salary per hour and certified classes.
 */
public class Instructor extends Person {

    /**
     * The salary per hour of the instructor.
     */
    private int _salary;

    /**
     * List of session types the instructor is certified to teach.
     */
    private ArrayList<SessionType> _certifiedClasses;

    /**
     * Constructs a new Instructor using an existing Person object and additional instructor-specific details.
     *
     * @param person           the Person object to copy details from
     * @param salary           the salary of the instructor
     * @param CertifiedClasses the list of session types the instructor is certified to teach
     */
    public Instructor(Person person, int salary, ArrayList<SessionType> CertifiedClasses) {
        super(person);
        this._certifiedClasses = new ArrayList<>(CertifiedClasses);
        this._salary = salary;
    }

    /**
     * Retrieves a copy of the list of certified classes.
     *
     * @return a list of session types the instructor is certified to teach
     */
    public ArrayList<SessionType> getCertifiedClasses() {
        return new ArrayList<>(_certifiedClasses);
    }

    /**
     * Retrieves the certified classes as a comma-separated string.
     *
     * @return a string representation of the certified classes
     */
    public String getCertifiedClassesAsString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < _certifiedClasses.size(); i++) {
            str.append(_certifiedClasses.get(i)).append(", ");
        }
        return str.substring(0, str.length() - 2);
    }

    /**
     * Retrieves the salary per hour of the instructor.
     *
     * @return the salary per hour of the instructor
     */
    public int get_salary() {
        return _salary;
    }

    /**
     * Returns a string representation of the instructor, including personal details, salary, and certified classes.
     *
     * @return a string representation of the instructor
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Role: Instructor | Salary per Hour: %d | Certified Classes: %s", _salary, getCertifiedClassesAsString());
    }

    /**
     * Compares this Instructor object to another for equality based on their unique IDs.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Instructor)) {
            return false;
        }
        Instructor other = (Instructor) obj;
        // Compare the ID field of the instructor
        return this.getID() == other.getID();
    }
}
