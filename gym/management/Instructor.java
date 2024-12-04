package gym.management;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor extends Person {
    private int _salary;
    private ArrayList<SessionType> _certifiedClasses;
    public Instructor(Person person, int salary, ArrayList<SessionType> CertifiedClasses) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getDateOfBirth());
        this._certifiedClasses = new ArrayList<>(CertifiedClasses);
        this._salary = salary;
    }

    public ArrayList<SessionType> getCertifiedClasses() {
        return new ArrayList<>(_certifiedClasses);
    }

    public int get_salary() {
        return _salary;
    }
}
