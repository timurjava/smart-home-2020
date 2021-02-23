package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.HallDoorClose;
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
    public void EventProcessing(SensorEvent event) {
            if (event.getType() == EventType.DOOR_CLOSED) {
                Action action = new HallDoorClose(event, smartHome);
                smartHome.act(action);
            }
        }
}


