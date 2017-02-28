package controller;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;
import entities.*;
import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by phoenix on 26.01.17.
 */
public class DispatcherCenter {

    private JChannel channel;
    private List<Aircraft> aircraftList = new ArrayList<>();
    private static final double rangeMax = 360;
    private static final double rangeMin = 50;

    public DispatcherCenter(JChannel channel, List aircraftList){
        this.channel = channel;
        this.aircraftList = aircraftList;
    }

    public JChannel getChannel() {
        return channel;
    }

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }

    public static void main(String[] args) {
        try {
            JChannel channel = new JChannel();
            Aircraft helicopter1 = new Helicopter(1,23.3,34, 21.2, Course.EAST, HelicopterType.MI_2);
            Aircraft helicopter2 = new Helicopter(2,23.3,34, 21.2, Course.EAST, HelicopterType.MI_171);
            Aircraft plane1 = new Airplane(3,23.3,34, 21.2, Course.EAST, AirplaneType.A_300);
            Aircraft plane2 = new Airplane(4,54.3, 23.11, 44, Course.NORTH_WEST, AirplaneType.B_777);
            List<Aircraft> list = new ArrayList<>();
            list.add(helicopter1);
            list.add(helicopter2);
            list.add(plane1);
            list.add(plane2);

            DispatcherCenter dispatcherCenter = new DispatcherCenter(channel, list);
            dispatcherCenter.getChannel().connect("Dispatcher");
            for(int i = 0; i < dispatcherCenter.getAircraftList().size(); i++){
                dispatcherCenter.getChannel().setReceiver(dispatcherCenter.getAircraftList().get(0));
                System.out.println("Before changing : " + dispatcherCenter.getAircraftList().get(i));
                Random random = new Random();
                double deltaLongitude = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
                double deltaLatitude = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
                double deltaAltitude = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
                NewOrder newData = new NewOrder(deltaLatitude, deltaLongitude, deltaAltitude);
                Gson gson = new Gson();
                View view = dispatcherCenter.channel.getView();
                Address node = view.getMembers().get(0);
                String json = gson.toJson(newData);
                Message message = new Message(node, json);
                Gson fromJson = new Gson();
                NewOrder newOrder = fromJson.fromJson((String) message.getObject(), NewOrder.class);
                dispatcherCenter.getAircraftList().get(i).update(newOrder);
                System.out.println("After changing : " + dispatcherCenter.getAircraftList().get(i));
                Thread.sleep(1000);
            }
            dispatcherCenter.getChannel().disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
