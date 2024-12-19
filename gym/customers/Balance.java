/**
 * The Balance class represents a person or gym balance in a gym management system.
 * It provides methods to add or subtract from the balance and retrieve the current balance.
 */
package gym.customers;

public class Balance {
    /**
     * The current balance of the customer.
     */
    private int _balance;

    /**
     * Constructs a new Balance object with the specified initial balance.
     *
     * @param balance the initial balance value
     */
    public Balance(int balance) {
        this._balance = balance;
    }

    /**
     * Adds the specified amount to the current balance.
     *
     * @param _balance the amount to be added
     */
    public void add(int _balance) {
        this._balance += _balance;
    }

    /**
     * Subtracts the specified amount from the current balance.
     *
     * @param i the amount to be subtracted
     */
    public void subtractBalance(int i) {
        this._balance -= i;
    }

    /**
     * Retrieves the current balance.
     *
     * @return the current balance
     */
    public int get_balance() {
        return _balance;
    }

    /**
     * Returns a string representation of the balance.
     *
     * @return the balance as a string
     */
    @Override
    public String toString() {
        return String.valueOf(_balance);
    }
}
