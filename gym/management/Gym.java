package gym.management;

public class Gym {
    private static Gym gym = new Gym();
    private Gym(){

    }
    public static Gym getInstance() {
        return gym;
    }

    public void setName(String crossFit) {
    }

    public void setSecretary(Person p1, int i) {
    }

    public Secretary getSecretary() {
    }
}
