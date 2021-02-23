package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.EventType.*;

public class LightEventHandler implements  Handler{
    private SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void EventProcessing(Event event) {
        SensorEvent exactEvent;
        if(event instanceof SensorEvent) {
            exactEvent = (SensorEvent) event;
            if (exactEvent.getType() == LIGHT_ON) {
                Action action = new TurnOnTheLights(exactEvent);
                smartHome.act(action);
            }
            if (exactEvent.getType() == LIGHT_OFF) {
                Action action = new TurnOffTheLights(exactEvent);
                smartHome.act(action);
            }
        }
    }
}
