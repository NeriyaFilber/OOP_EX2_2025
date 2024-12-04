package gym.management;

public class Person {

    private String _name;
    private int _balance;
    private Gender _gender;
    private String _dateOfBirth;

    public Person(String name, int balance, Gender gender, String dateOfBirth){
        this._name = name;
        this._balance = balance;
        this._gender = gender;
        this._dateOfBirth = dateOfBirth;

    }

    protected String getName() {
        return _name;
    }
}
