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
    public Instructor(Person person,ArrayList<SessionType> CertifiedClasses,int salary){
        super(person);
        this._certifiedClasses = new ArrayList<>(CertifiedClasses);
        this._salary = salary;
    }


    public ArrayList<SessionType> getCertifiedClasses() {
        return new ArrayList<>(_certifiedClasses);
    }
    public String getCertifiedClassesAsString() {
        return String.join(", ", _certifiedClasses.stream().map(Enum::name).toArray(String[]::new));
    }

    public int get_salary() {
        return _salary;
    }

    public String get_role() {
        return "Instructor";
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format("  | Role: Instructor | Salary per Month: %d | Certified Classes: %s", _salary,getCertifiedClassesAsString());
    }
}
