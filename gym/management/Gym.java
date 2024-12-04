package gym.management;

import gym.Exception.InstructorNotQualifiedException;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

public class Gym {
    private static Gym gym = new Gym();
    private static Secretary secretary;
    private  String _nameGym;
    private Gym(){

    }
    public static Gym getInstance() {
        return gym;
    }

    public void setName(String crossFit) {
        this._nameGym = crossFit;
    }

    public void setSecretary(Person p1, int i) {

    }

    public Secretary getSecretary() {
        return secretary;

    }
}
