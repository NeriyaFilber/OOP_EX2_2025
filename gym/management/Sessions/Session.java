package gym.management.Sessions;

import gym.Exception.DuplicateClientException;
import gym.management.Instructor;
import gym.management.Person;

import java.awt.*;
import java.util.ArrayList;

//TODO update date in class(constructor, data member, and session date function)
public abstract class Session {
    private ForumType _forum;
    private Instructor _instructor;
    private ArrayList<Person> _participant = new ArrayList<>();

    public Session(String date, ForumType forumType, Instructor instructor){
        this._forum = forumType;
        this._instructor = instructor;
    }
    /**
     * Retrieves the type of the session.
     *
     * @return the type of the session as a {@link SessionType}.
     */
    public abstract SessionType getType();
//   public sessionDate //TODO write a date time when working.
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
    public boolean addParticipant(Person person){
        if(_participant.contains(person)){
            return false;
        }
        _participant.add(person);
        return true;
    }

    public ForumType get_forum() {
        return _forum;
    }

    public Instructor get_instructor() {
        return _instructor;
    }

    public ArrayList<Person> get_participant() {
        return new ArrayList<>(_participant);
    }

    public abstract int getCost();
    public abstract int numOfParticipant();
}
