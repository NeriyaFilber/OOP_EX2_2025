/**
 * The Person class serves as a base class for all types of individuals in the gym management system,
 * including clients, secretaries, and instructors.
 * It provides common attributes and methods for managing personal details and balance.
 */
package gym.customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Represents a base entity with personal details and balance for all individuals in the gym system.
 */
public class Person {

    /**
     * Counter for generating unique IDs.
     */
    private static int _COUNTER_ID = 1111;

    /**
     * The name of the individual.
     */
    private String _name;

    /**
     * The balance associated with the individual.
     */
    private Balance _balance;

    /**
     * The gender of the individual.
     */
    private Gender _gender;

    /**
     * The date of birth of the individual in the format "dd-MM-yyyy".
     */
    private String _dateOfBirth;

    /**
     * The unique ID of the individual.
     */
    private int _ID;

    /**
     * The calculated age of the individual.
     */
    private int _age;

    /**
     * Constructs a new Person object with the specified details.
     *
     * @param name        the name of the individual
     * @param balance     the initial balance
     * @param gender      the gender of the individual
     * @param dateOfBirth the date of birth in the format "dd-MM-yyyy"
     */
    public Person(String name, int balance, Gender gender, String dateOfBirth) {
        this._name = name;
        this._balance = new Balance(balance);
        this._gender = gender;
        this._dateOfBirth = dateOfBirth;
        this._age = calculateAge(dateOfBirth);
        this._ID = _COUNTER_ID;
        _COUNTER_ID++;
    }

    /**
     * Copy constructor to create a new Person object with the same details as another.
     *
     * @param person the Person object to copy
     */
    public Person(Person person) {
        this._name = person.getName();
        this._balance = person.getBalance();
        this._dateOfBirth = person.getDateOfBirth();
        this._gender = person.getGender();
        this._ID = person.getID();
        this._age = person.getAge();
    }

    /**
     * Sets a new balance for the individual.
     *
     * @param balance the new balance value
     */
    public void set_balance(int balance) {
        this._balance = new Balance(balance);
    }

    /**
     * Adds the specified amount to the individual's balance.
     *
     * @param balance the amount to add
     */
    public void addBalance(int balance) {
        _balance.add(balance);
    }

    /**
     * Subtracts the specified amount from the individual's balance.
     *
     * @param i the amount to subtract
     */
    public void subtractBalance(int i) {
        _balance.subtractBalance(i);
    }

    /**
     * Retrieves the individual's name.
     *
     * @return the name of the individual
     */
    public String getName() {
        return _name;
    }

    /**
     * Retrieves the individual's balance.
     *
     * @return the balance of the individual
     */
    public Balance getBalance() {
        return _balance;
    }

    /**
     * Retrieves the individual's gender.
     *
     * @return the gender of the individual
     */
    public Gender getGender() {
        return _gender;
    }

    /**
     * Retrieves the individual's date of birth.
     *
     * @return the date of birth of the individual
     */
    public String getDateOfBirth() {
        return _dateOfBirth;
    }

    /**
     * Retrieves the individual's unique ID.
     *
     * @return the ID of the individual
     */
    public int getID() {
        return _ID;
    }

    /**
     * Retrieves the individual's age.
     *
     * @return the age of the individual
     */
    public int getAge() {
        return _age;
    }

    /**
     * Calculates the individual's age based on their date of birth.
     *
     * @param dateOfBirth the date of birth in the format "dd-MM-yyyy"
     * @return the calculated age
     */
    private int calculateAge(String dateOfBirth) { // Temporary method for testing purposes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
        LocalDate futureDate = LocalDate.of(2025, 1, 1);
        int age = Period.between(birthDate, futureDate).getYears();
        return age;
    }
    //    private int calculateAge(String dateOfBirth){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        int age = Period.between(LocalDate.parse(dateOfBirth,formatter), LocalDate.now()).getYears();
//        return age;
//}

    /**
     * Returns a string representation of the individual's details.
     *
     * @return a formatted string containing individual's details
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %s",
                this._ID,
                this._name,
                this._gender,
                this._dateOfBirth,
                this._age,
                this._balance);
    }

    /**
     * Compares this Person object to another for equality based on their unique IDs.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        return this._ID == other._ID;
    }
}
