package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.handlers.Handler;

public class AlarmActivationEventHandler implements Handler {
    private final Alarm alarm;

    public AlarmActivationEventHandler(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void EventProcessing(Event event) {
        if (event instanceof AlarmActivationEvent) {
            String code = ((AlarmActivationEvent) event).getCode();
            alarm.activate(code);
        }
    }
}
