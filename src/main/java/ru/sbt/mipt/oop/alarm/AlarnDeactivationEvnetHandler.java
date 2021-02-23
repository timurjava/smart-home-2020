package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.handlers.Handler;

public class AlarnDeactivationEvnetHandler implements Handler {
    private final Alarm alarm;

    public AlarnDeactivationEvnetHandler(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void EventProcessing(Event event) {
        if (event instanceof AlarmDeactivationEvent) {
            String code = ((AlarmDeactivationEvent) event).getCode();
            alarm.deactivate(code);
        }
    }
}
