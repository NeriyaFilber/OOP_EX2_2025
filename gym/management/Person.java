package gym.management;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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
        this._age = calculateAge(dateOfBirth);
        this._ID = _COUNTER_ID;
        _COUNTER_ID++;
    }

    public Person(Person person){
        this._name = person.getName();
        this._balance = person.getBalance();
        this._dateOfBirth = person.getDateOfBirth();
        this._gender = person.getGender();
        this._ID = person.getID();
        this._age = person.getAge();
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

    private int calculateAge(String dateOfBirth){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int age = Period.between(LocalDate.parse(dateOfBirth,formatter), LocalDate.now()).getYears();
        return age;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d",
                this._ID,
                this._name,
                this._gender,
                this._dateOfBirth,
                this._age,
                this._balance);
    }
}



