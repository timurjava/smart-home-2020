package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SensorEvent;

public class TurnOnTheLights implements Action {
    private String id;

    public TurnOnTheLights(SensorEvent event) {
        this.id = event.getObjectId();
    }

    @Override
    public void doAction(Object object) {
        String dislocation = null;
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
