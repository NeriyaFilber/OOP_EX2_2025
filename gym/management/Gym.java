package gym.management;

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
