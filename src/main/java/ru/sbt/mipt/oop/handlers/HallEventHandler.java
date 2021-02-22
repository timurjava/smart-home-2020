package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.HallDoorClose;
import ru.sbt.mipt.oop.sender.ComandSender;


public class HallEventHandler implements Handler{
    private SmartHome smartHome;
    private final ComandSender comandSender;

    public HallEventHandler(SmartHome smartHome, ComandSender comandSender){
        this.smartHome = smartHome;
        this.comandSender = comandSender;
    }

    @Override
    public void EventProcessing(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            Action action = new HallDoorClose(event, smartHome);
            smartHome.act(action);
        }
    }
}

