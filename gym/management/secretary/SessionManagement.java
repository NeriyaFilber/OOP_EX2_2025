package gym.management.secretary;

import gym.Exception.InstructorNotQualifiedException;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionFactory;
import gym.management.Sessions.SessionType;

public class SessionManagement {
    GymManagementSystem gymSystem = GymManagementSystem.getInstance();



    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        GymManagementSystem gymSystem = GymManagementSystem.getInstance();

        if (!instructor.getCertifiedClasses().contains(sessionType)){
            throw InstructorNotQualifiedException.getInstance();
        }
        Session session = SessionFactory.createSession(sessionType,s,forumType,instructor);
        gymSystem.addSession(session);

        String mes = new String("Created new session: " + sessionType + " on " + session.getDate() + " with instructor: "+ instructor.getName());
        ActionLogManager.getInstance().logAction(mes);//TODO add date
        return session;
    }

}
