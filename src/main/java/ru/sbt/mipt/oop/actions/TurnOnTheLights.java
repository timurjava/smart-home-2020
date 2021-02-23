package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.events.SensorEvent;

public class TurnOnTheLights implements Action {
    private String id;
    private String dislocation;

    public TurnOnTheLights(SensorEvent event) {
        this.id = event.getObjectId();
    }

    @Override
    public void doAction(Object object) {
        if (object instanceof Room) {
            dislocation = ((Room) object).getName();
        }
        if (object instanceof Light) {
            if (((Light) object).getId().equals(id)) {
                ((Light) object).setOn(true);
                System.out.println("Light " + id + " in room " + dislocation + " was turned on.");
            }
        }
    }
}
