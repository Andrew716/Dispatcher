package entities;

/**
 * Created by phoenix on 25.02.17.
 */

public class Airplane extends Aircraft {

    private AirplaneType type;

    public Airplane(int number, AirplaneType type) {
        super(number);
        this.type = type;
    }

    public Airplane(int number, double latitude, double longitude, double altitude, Course course, AirplaneType type) {
        super(number, latitude, longitude, altitude, course);
        this.type = type;
    }

    public AirplaneType getType() {
        return type;
    }

    public void setType(AirplaneType type) {
        this.type = type;
    }
}
