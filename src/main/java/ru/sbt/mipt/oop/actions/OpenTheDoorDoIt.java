package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.events.SensorEvent;

public class OpenTheDoorDoIt implements Action {
    private String id;

    public OpenTheDoorDoIt(SensorEvent event){
        this.id = event.getObjectId();
    }

    @Override
    public void doAction(Object object) {
        String dislocation = null;
        if(object instanceof Room){
            dislocation = ((Room) object).getName();
        }
        if(object instanceof Door){
            if (((Door) object).getId().equals(id)) {
                ((Door) object).setOpen(false);
                System.out.println("Door " + id + " in room " + dislocation + " was opened.");
            }
        }
    }
}
