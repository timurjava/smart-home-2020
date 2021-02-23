package ru.sbt.mipt.oop.handlers;

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
    public void EventProcessing(SensorEvent event) {
            if (event.getType() == LIGHT_ON) {
                Action action = new TurnOnTheLights(event);
                smartHome.act(action);
            }
            if (event.getType() == LIGHT_OFF) {
                Action action = new TurnOffTheLights(event);
                smartHome.act(action);
            }
        }
}
