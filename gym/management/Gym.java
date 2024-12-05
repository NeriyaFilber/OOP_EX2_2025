package gym.management;


public class Gym {
    private static Gym gym;
    private static Secretary _secretary;
    private  String _nameGym;
    private Gym(){

    }
    public static Gym getInstance() {
        if (gym == null){
            gym = new Gym();
        }
        return gym;
    }

    public void setName(String crossFit) {
        this._nameGym = crossFit;
    }

    public void setSecretary(Person p1, int i) {
        if(_secretary == null) {
            _secretary = new Secretary(p1, i);
        }
        Secretary secretary = new Secretary(p1,i);
        secretary.copySecretary(_secretary);
        _secretary.clearSecretary();
        _secretary = secretary;

    }

    public Secretary getSecretary() {
        return _secretary;

    }
}
