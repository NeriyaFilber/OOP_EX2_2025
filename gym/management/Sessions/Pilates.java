package gym.management.Sessions;

import gym.management.Instructor;

public class Pilates extends Session{
    public static final int SESSION_COST = 60;
    public static final int MAX_PARTICIPANT = 30;

    public Pilates(String date, ForumType forumType, Instructor instructor){
        super(date, forumType, instructor);
    }
    @Override
    public SessionType getType() {
        return SessionType.Pilates;
    }

    @Override
    public int getCost() {
        return SESSION_COST;
    }

    @Override
    public int maxNumOfParticipant() {
        return MAX_PARTICIPANT;
    }

    @Override
    public String toString() {
        return String.format("Session Type: %s | ", SessionType.Pilates) + super.toString() + "/" + MAX_PARTICIPANT;
    }
}
