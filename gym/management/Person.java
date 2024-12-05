package gym.management;

public class Person {
    private static int _COUNTER_ID = 1111;
    private String _name;
    private int _balance;
    private Gender _gender;
    private String _dateOfBirth;
    private int _ID;
    private int _age;

    public Person(String name, int balance, Gender gender, String dateOfBirth) {
        this._name = name;
        this._balance = balance;
        this._gender = gender;
        this._dateOfBirth = dateOfBirth;
        this._ID = _COUNTER_ID;
        _COUNTER_ID++;
    }

    public Person(Person person){
        this._name = person.getName();
        this._balance = person.getBalance();
        this._dateOfBirth = person.getDateOfBirth();
        this._gender = person.getGender();
        this._ID = person.getID();
    }

    public void set_balance(int balance) {
        this._balance = _balance + balance;
    }

    public String getName() {
        return _name;
    }

    public int getBalance() {
        return _balance;
    }

    public Gender getGender() {
        return _gender;
    }

    public String getDateOfBirth() {
        return _dateOfBirth;
    }

    public int getID() {
        return _ID;
    }

    public int getAge() {
        return _age;
    }
}



