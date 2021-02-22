package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public class HallDoorClose implements Action{
    private String id;
    private SmartHome smartHome;
    String dislocation;

    public HallDoorClose(SensorEvent event, SmartHome smartHome) {
        this.id = event.getObjectId();
        this.smartHome = smartHome;
    }

    @Override
    public void doAction(Object object) {
        if (object instanceof Room) {
            dislocation = ((Room) object).getName();
        }
        if (object instanceof Door) {
            if (((Door) object).getId().equals(id) && dislocation.equals("hall")) {
                Action action = new TurnOffAllTheLights();
                smartHome.act(action);
                System.out.println("All lights were turned off");
            }
        }
    }
}
