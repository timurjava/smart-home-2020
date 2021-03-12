package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.Alarm.*;
import ru.sbt.mipt.oop.Events.AlarmEvent;
import ru.sbt.mipt.oop.Events.Event;
import ru.sbt.mipt.oop.SmartHome;

public class SecurityProcessorDecorator implements EventHandler {
    private final EventHandler wrappeeProcessor;
    private final Sender messageSender;
    private final SmartHome smartHome;

    public SecurityProcessorDecorator(EventHandler wrappeeProcessor, Sender messageSender,
                                      SmartHome smartHome) {
        this.wrappeeProcessor = wrappeeProcessor;
        this.messageSender = messageSender;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Event event) {
        Alarm alarm = smartHome.getAlarm();
        if (alarm.getState() instanceof AlarmDeactivated ||(alarm.getState() instanceof AlarmActiveState && event instanceof AlarmEvent)) {
            wrappeeProcessor.handleEvent(event);
        }

        if (alarm.getState() instanceof AlarmActivated ||alarm.getState() instanceof AlarmActiveState){
            messageSender.send("Try change state of home; " + event);
        }

        if (alarm.getState() instanceof AlarmActivated){
            alarm.setState(new AlarmActiveState(alarm));
        }
    }
}