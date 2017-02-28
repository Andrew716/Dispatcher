package entities;

/**
 * Created by phoenix on 25.02.17.
 */
public class Helicopter extends Aircraft{

    HelicopterType type;

    public Helicopter(int number, HelicopterType type) {
        super(number);
        this.type = type;
    }

    public Helicopter(int number, double latitude, double longitude, double altitude, Course course, HelicopterType type) {
        super(number, latitude, longitude, altitude, course);
        this.type = type;
    }

    public HelicopterType getType() {
        return type;
    }

    public void setType(HelicopterType type) {
        this.type = type;
    }
}
