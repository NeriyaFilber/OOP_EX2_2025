package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor {
    private int _salary;
    private ArrayList<SessionType> _certifiedClasses;
    private Person _person;
    public Instructor(Person person, int salary, ArrayList<SessionType> CertifiedClasses) {
        this._person = person;
        this._certifiedClasses = new ArrayList<>(CertifiedClasses);
        this._salary = salary;
    }

    public ArrayList<SessionType> getCertifiedClasses() {
        return new ArrayList<>(_certifiedClasses);
    }
    public String getCertifiedClassesAsString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < _certifiedClasses.size(); i++) {
            str.append(_certifiedClasses.get(i)).append(", ");
        }
        return str.substring(0,str.length()-2);
    }

    public int get_salary() {
        return _salary;
    }
    @Override
    public String toString() {
        return  _person.toString() + String.format("  | Role: Instructor | Salary per Hour: %d | Certified Classes: %s", _salary , getCertifiedClassesAsString());
    }

    public Person get_person() {
        return _person;
    }
    public void add_balance(int i){
        _person.set_balance(_person.getBalance() +i);
    }

    public String getName(){
        return _person.getName();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Instructor)) {
            return false;
        }
        Instructor other = (Instructor) obj;
        // כאן השווה את שדות המחלקה כדי לוודא ששני המדריכים הם אותו אובייקט
        return this._person.getID() == other._person.getID(); // לדוגמה, אם המדריכים מזהים לפי ID
    }


}
