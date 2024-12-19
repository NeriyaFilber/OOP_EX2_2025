package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The {@code Session} class is an abstract representation of a gym session.
 * It provides the common functionality and attributes shared by all types of sessions in the gym management system.
 * Examples of concrete session types extending this class include Pilates, MachinePilates, and Ninja training.
 */
public abstract class Session {

    /**
     * The forum type of the session, indicating the target audience (e.g., All, Female, Seniors, Male).
     */
    private ForumType _forum;

    /**
     * The instructor leading the session.
     */
    private Instructor _instructor;

    /**
     * A list of clients participating in the session.
     */
    private ArrayList<Client> _participant = new ArrayList<>();

    /**
     * The date and time the session is scheduled for.
     */
    private LocalDateTime _date;

    /**
     * Constructs a new {@code Session} with the specified date, forum type, and instructor.
     *
     * @param date          the date of the session in the format "dd-MM-yyyy HH:mm".
     * @param forumType     the forum type associated with the session.
     * @param instructor    the instructor leading the session.
     */
    public Session(String date, ForumType forumType, Instructor instructor) {
        this._forum = forumType;
        this._instructor = instructor;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this._date = LocalDateTime.parse(date, formatter);
    }

    /**
     * Retrieves the type of the session.
     *
     * @return the type of the session as a {@link SessionType}.
     */
    public abstract SessionType getType();

    /**
     * Formats the session date into a string.
     *
     * @return the formatted session date in the format "dd-MM-yyyy HH:mm".
     */
    public String sessionDateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return _date.format(formatter);
    }

    /**
     * Formats the session date to include only the day, month, and year.
     *
     * @return the formatted session date in the format "dd-MM-yyyy".
     */
    public String sessionDateYear() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return _date.format(formatter);
    }

    /**
     * Retrieves the forum type of the session.
     *
     * @return the forum type as a {@link ForumType}.
     */
    public ForumType getForum() {
        return this._forum;
    }

    /**
     * Retrieves the instructor leading the session.
     *
     * @return the instructor as a {@link Instructor}.
     */
    public Instructor getInstructor() {
        return this._instructor;
    }

    /**
     * Adds a client to the session if the client is not already a participant and the session is not full.
     *
     * @param client the {@link Client} to add.
     * @return {@code true} if the client is successfully added, otherwise {@code false}.
     */
    public boolean addParticipant(Client client) {
        if (_participant.contains(client) || _participant.size() >= maxNumOfParticipant()) {
            return false;
        }
        _participant.add(client);
        return true;
    }

    /**
     * Removes a client from the session if the client is a participant.
     *
     * @param client the {@link Client} to remove.
     * @return {@code true} if the client is successfully removed, otherwise {@code false}.
     */
    public boolean removeParticipant(Client client) {
        if (_participant.isEmpty() || !_participant.contains(client)) {
            return false;
        }
        _participant.remove(client);
        return true;
    }

    /**
     * Retrieves the date of the session as a string.
     *
     * @return the session date as a string.
     */
    public String getDate() {
        return _date.toString();
    }

    /**
     * Retrieves the date of the session as a {@link LocalDateTime} object.
     *
     * @return the session date.
     */
    public LocalDateTime getDateAsLD() {
        return _date;
    }

    /**
     * Retrieves a list of all participants in the session.
     *
     * @return a copy of the {@link ArrayList} containing all participants.
     */
    public ArrayList<Client> get_participant() {
        return new ArrayList<>(_participant);
    }

    /**
     * Retrieves a list of all clients in the session.
     *
     * @return a copy of the {@link ArrayList} containing all clients.
     * Note: This is an alias for {@link #get_participant()}.
     */
    public ArrayList<Client> get_Client() {
        return new ArrayList<>(_participant);
    }

    /**
     * Retrieves the cost of the session.
     *
     * @return the cost as an integer.
     */
    public abstract int getCost();

    /**
     * Retrieves the maximum number of participants allowed in the session.
     *
     * @return the maximum number of participants as an integer.
     */
    public abstract int maxNumOfParticipant();

    /**
     * Converts a date string into a {@link LocalDateTime} object.
     *
     * @param dateStr the date string in the format "dd-MM-yyyy HH:mm".
     * @return the corresponding {@link LocalDateTime} object.
     */
    public LocalDateTime stringToLocalDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * Retrieves the instructor for the session (alias for {@link #getInstructor()}).
     *
     * @return the {@link Instructor} leading the session.
     */
    public Instructor get_instructor() {
        return _instructor;
    }

    /**
     * Retrieves the number of participants currently registered for the session.
     *
     * @return the number of participants as an integer.
     */
    public int getNumOfParticipant() {
        return _participant.size();
    }

    /**
     * Returns a string representation of the session, including its date, forum type, instructor, and number of participants.
     *
     * @return a formatted string containing session details.
     */
    @Override
    public String toString() {
        return String.format("Date: %s | Forum: %s | Instructor: %s | Participants: %d",
                this.sessionDateToString(),
                this._forum,
                this._instructor.getName(),
                this._participant.size());
    }
}