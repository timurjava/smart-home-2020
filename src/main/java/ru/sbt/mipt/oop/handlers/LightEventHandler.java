package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.*;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventHandler implements  Handler{
    private SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void EventProcessing(SensorEvent event) {
        if(event.getType() == LIGHT_ON){
            Action action = new TurnOnTheLights(event);
            smartHome.act(action);
        }
        if(event.getType() == LIGHT_OFF){
            Action action = new TurnOffTheLights(event);
            smartHome.act(action);
        }
    }
}
