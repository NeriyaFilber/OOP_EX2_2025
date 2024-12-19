package gym.management.Sessions;

import gym.customers.Instructor;

public class SessionFactory {
    public static Session createSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor){
        return switch (sessionType) {
            case Ninja -> new Ninja(s, forumType, instructor);
            case Pilates -> new Pilates(s, forumType, instructor);
            case ThaiBoxing -> new ThaiBoxing(s, forumType, instructor);
            case MachinePilates -> new MachinePilates(s, forumType, instructor);
        };
    }
}
