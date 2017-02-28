package entities;

import org.jgroups.ReceiverAdapter;

/**
 * Created by phoenix on 26.01.17.
 */
public class Aircraft extends ReceiverAdapter {


    private int number;
    private double latitude;
    private double longitude;
    private double altitude;
    private Course course;

    public Aircraft(int number) {
        this.number = number;
    }

    public Aircraft(int number, double latitude, double longitude, double altitude, Course course) {
        this.number = number;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.course = course;
    }

    public int getNumber() {
        return number;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public Course getCourse() {
        return course;
    }

    public void update(NewOrder newOrder){
        this.latitude += newOrder.getNewLatitude();
        this.longitude += newOrder.getNewLongitude();
        this.altitude += newOrder.getNewAltitude();
    }

    @Override
    public String toString() {
        return this.getClass() +
                "number=" + number +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", course=" + course +
                '}';
    }
}
