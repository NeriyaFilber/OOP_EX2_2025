package gym.customers;

import java.util.ArrayList;

public class Client {

    private ArrayList<String> _notifications = new ArrayList<>();
    private Person person;



    public Client(Person person) {
        this.person = person;
    }

    public String getNotifications() {
        if (_notifications.isEmpty()) {
            return "[]";
        }
        StringBuilder ans = new StringBuilder("[");
        for (String not : _notifications) {
            ans.append(not);
            ans.append(", ");
        }
        ans.delete(ans.length()-2 , ans.length()); //remove the last chars ", "
        ans.append("]");
        return ans.toString();
    }
    public void subtractBalance(int i){
        person.set_balance(person.getBalance()-i);
    }

    public Person getPerson() {
        return person;
    }

    public String getName(){
        return person.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // אם זה אותו אובייקט
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // אם אובייקט שונה או null
        }
        Client other = (Client) obj;
        // השווה את שדה ה-ID של הלקוח
        return this.person.getID() == other.person.getID(); // נניח ש-ID הוא שדה מזהה ייחודי
    }


    @Override
    public String toString() {
        return person.toString();
    }
    public void addNotification(String notification) {
        _notifications.add(notification);
    }



}
