package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.HallDoorClose;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.sender.Sender;


public class HallEventHandler implements Handler{
    private SmartHome smartHome;
    private final Sender sender;

    public HallEventHandler(SmartHome smartHome, Sender sender){
        this.smartHome = smartHome;
        this.sender = sender;
    }

    @Override
    public void EventProcessing(Event event) {
        SensorEvent exactEvent;
        if(event instanceof SensorEvent) {
            exactEvent = (SensorEvent) event;
            if (exactEvent.getType() == EventType.DOOR_CLOSED) {
                Action action = new HallDoorClose(exactEvent, smartHome);
                smartHome.act(action);
            }
        }
    }
}


