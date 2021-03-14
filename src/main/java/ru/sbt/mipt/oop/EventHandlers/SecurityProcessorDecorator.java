package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.Alarm.*;
import ru.sbt.mipt.oop.Events.AlarmEvent;
import ru.sbt.mipt.oop.Events.Event;
import ru.sbt.mipt.oop.SmartHome;

public class SecurityProcessorDecorator implements EventHandlerMy {
    private final EventHandlerMy wrappeeProcessor;
    private final Sender messageSender;
    private final SmartHome smartHome;

    public SecurityProcessorDecorator(EventHandlerMy wrappeeProcessor, Sender messageSender,
                                      SmartHome smartHome) {
        this.wrappeeProcessor = wrappeeProcessor;
        this.messageSender = messageSender;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Event event) {
        Alarm alarm = smartHome.getAlarm();
        if (alarm.isDeactivated()||(alarm.isInDanger() && event instanceof AlarmEvent)) {
            wrappeeProcessor.handleEvent(event);
        }

        if (alarm.isActivated() || alarm.isInDanger()){
            messageSender.send("Try change state of home; " + event);
        }

        if (alarm.isActivated()){
            alarm.trigger();
        }
    }
}