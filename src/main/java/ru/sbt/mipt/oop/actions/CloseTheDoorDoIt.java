package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.events.SensorEvent;

public class CloseTheDoorDoIt implements Action {
    private String id;
    private String dislocation;


    public CloseTheDoorDoIt(SensorEvent event){
        this.id = event.getObjectId();
    }

    @Override
    public void doAction(Object object) {
        if(object instanceof Room){
            dislocation = ((Room) object).getName();
        }
        if(object instanceof  Door){
            if (((Door) object).getId().equals(id)) {
                ((Door) object).setOpen(false);
                System.out.println("Door " + id + " in room " + dislocation + " was closed.");
            }
        }
    }
}
