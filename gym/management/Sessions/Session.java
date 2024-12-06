package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;
import gym.management.Person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//TODO update date in class(constructor, data member, and session date function)
public abstract class Session {
    private ForumType _forum;
    private Instructor _instructor;
    private ArrayList<Client> _participant = new ArrayList<>();
    private LocalDateTime _date;

    public Session(String date, ForumType forumType, Instructor instructor){
        this._forum = forumType;
        this._instructor = instructor;
//        this._date = String.valueOf(stringToLocalDateTime(date));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this._date = LocalDateTime.parse(date, formatter);
    }
    /**
     * Retrieves the type of the session.
     *
     * @return the type of the session as a {@link SessionType}.
     */
    public abstract SessionType getType();
    public String sessionDateToString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return _date.format(formatter);
    }
    /**
     * Retrieves the forum associated with the session.
     *
     * @return the forum as a {@link ForumType}.
     */
    public ForumType getForum(){
        return this._forum;
    }
    /**
     * Retrieves the instructor for the session.
     *
     * @return the instructor as a {@link Instructor}.
     */
    public Instructor getInstructor(){
        return this._instructor;
    }
    public boolean addParticipant(Client client){
        if(_participant.contains(client) || _participant.size() >= maxNumOfParticipant()){
            return false;
        }
        _participant.add(client);
        return true;
    }
    public boolean removeParticipant(Client client){
        if (_participant.isEmpty() || !_participant.contains(client)){
            return false;
        }
        _participant.remove(client);
        return true;
    }

    public String getDate() {
        return _date.toString();
    }

    public LocalDateTime getDateAsLD() {
        return _date;
    }

    public ArrayList<Person> get_participant() {
        return new ArrayList<>(_participant);
    }

    public abstract int getCost();
    public abstract int maxNumOfParticipant();

    public LocalDateTime stringToLocalDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateStr, formatter);
    }

    public Instructor get_instructor() {
        return _instructor;
    }
    public int getNumOfParticipant(){
        return _participant.size();
    }
    @Override
    public String toString(){
        return String.format("Date: %s | Forum: %s | Instructor: %s | Participants: %d",
                this.sessionDateToString(),
                this._forum,
                this._instructor.getName(),
                this._participant.size());
    }
}
