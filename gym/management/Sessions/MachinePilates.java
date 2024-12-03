package gym.management.Sessions;

import gym.management.Instructor;

public class MachinePilates extends Session{
    public static final int SESSION_COST = 80;
    public static final int MAX_PARTICIPANT = 10;

    public MachinePilates(String date, ForumType forumType, Instructor instructor){
        super(date, forumType, instructor);
    }
    @Override
    public SessionType getType() {
        return SessionType.MachinePilates;
    }

    @Override
    public int getCost() {
        return SESSION_COST;
    }

    @Override
    public int numOfParticipant() {
        return MAX_PARTICIPANT;
    }
}
