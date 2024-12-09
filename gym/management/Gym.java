package gym.management;


import javax.management.relation.Role;
import java.util.ArrayList;

public class Gym {
    private static Gym gym;
    private static Secretary _secretary;
    private static int _salary;
    private  String _nameGym;
    private Gym(){

    }
    public static Gym getInstance() {
        if (gym == null){
            gym = new Gym();
        }
        return gym;
    }

    public void setName(String crossFit) {
        this._nameGym = crossFit;
    }

    public void setSecretary(Person p1, int i) {
        if(_secretary == null) {
            _secretary = new Secretary(p1, i);
        }
        int balance = p1.getBalance();
        Secretary secretary = new Secretary(p1,i);
        secretary.copySecretary(_secretary);
        _secretary.clearSecretary();
        _secretary = secretary;
        secretary.get_secretary().set_balance(balance);
        secretary.get_allWork(p1);
        _salary = i;
        secretary.logAction("A new secretary has started working at the gym: " + p1.getName());

    }

    public Secretary getSecretary() {
        return _secretary;

    }


    @Override
    public String toString() {
        return "Gym Name: " + _nameGym + "\n"+
                "Gym Secretary: ID: " + _secretary.get_secretary().getID() + " | Name: " + _secretary.get_secretary().getName() +
                " | Gender: " + _secretary.get_secretary().getGender() + " | Birthday: " +
                _secretary.get_secretary().getDateOfBirth() + " | Age: " +
                _secretary.get_secretary().getAge() + " | Balance: " + _secretary.get_secretary().getBalance() +
                " | Role: " + _secretary.get_role() + " | Salary per Month: "+
                _salary + "\n" + "Gym Balance: " + _secretary.get_gymBalance() + "\n" + _secretary;
    }


}
