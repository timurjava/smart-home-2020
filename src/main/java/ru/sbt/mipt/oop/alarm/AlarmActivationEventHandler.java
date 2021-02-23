package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.handlers.Handler;

public class AlarmActivationEventHandler implements Handler {
    private final Alarm alarm;

    public AlarmActivationEventHandler(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void EventProcessing(SensorEvent event) {
        if (event.getType() == EventType.ALARM_ACTIVATE) {
            alarm.activate(event.getCode());
        }
    }
}
