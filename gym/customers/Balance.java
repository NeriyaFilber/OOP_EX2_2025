package gym.customers;

public class Balance {
    private int _balance;

    public Balance(int balance) {
        this._balance = balance;
    }
    public void add(int _balance) {
        this._balance += _balance;
    }
    public void subtractBalance(int i){
        this._balance -= i;;
    }

    public int get_balance() {
        return _balance;
    }
    @Override
    public String toString() {
        return String.valueOf(_balance);
    }
}
