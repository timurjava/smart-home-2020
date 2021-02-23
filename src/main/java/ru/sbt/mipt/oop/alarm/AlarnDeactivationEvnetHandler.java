package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.handlers.Handler;

public class AlarnDeactivationEvnetHandler implements Handler {
    private final Alarm alarm;

    public AlarnDeactivationEvnetHandler(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void EventProcessing(SensorEvent event) {
        if (event.getType() == EventType.ALARM_DEACTIVATE) {
            alarm.deactivate(event.getCode());
        }
    }
}
