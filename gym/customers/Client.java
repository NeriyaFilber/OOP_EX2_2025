package gym.customers;

import java.util.ArrayList;

public class Client extends Person  {

    private ArrayList<String> _notifications = new ArrayList<>();

    public Client(String name, int balance, Gender gender, String dateOfBirth) {
        super(name, balance, gender, dateOfBirth);
    }

    public Client(Person person) {
        super(person);
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
        return this.getID() == other.getID(); // נניח ש-ID הוא שדה מזהה ייחודי
    }


    public void addNotification(String notification) {
        _notifications.add(notification);
    }



}
