package gym.management.Sessions;

import gym.customers.Instructor;

public class SessionFactory {
    public static Session createSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor){
        switch (sessionType){
            case Ninja:
                return new Ninja(s,forumType,instructor);
            case Pilates:
                return new Pilates(s,forumType,instructor);
            case ThaiBoxing:
                return new ThaiBoxing(s,forumType,instructor);
            case MachinePilates:
                return new MachinePilates(s,forumType,instructor);
        }
        return null;
    }
}
