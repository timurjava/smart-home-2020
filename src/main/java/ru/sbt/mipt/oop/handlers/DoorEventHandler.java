package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.CloseTheDoorDoIt;
import ru.sbt.mipt.oop.actions.OpenTheDoorDoIt;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.EventType.DOOR_OPEN;


public class DoorEventHandler implements Handler {
    private SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void EventProcessing(Event event) {
        SensorEvent exactEvent;
        if (event instanceof SensorEvent) {
            exactEvent = (SensorEvent) event;
            if (exactEvent.getType() == DOOR_OPEN) {
                Action action = new OpenTheDoorDoIt(exactEvent);
                smartHome.act(action);
            }
            if (exactEvent.getType() == DOOR_CLOSED) {
                Action action = new CloseTheDoorDoIt(exactEvent);
                smartHome.act(action);
            }
        }
    }
}

