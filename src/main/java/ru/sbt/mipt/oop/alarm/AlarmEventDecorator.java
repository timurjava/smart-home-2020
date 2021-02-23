package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.Chooser;
import ru.sbt.mipt.oop.events.EventHandlerStarter;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.handlers.Handler;
import ru.sbt.mipt.oop.sender.Sender;

public class AlarmEventDecorator implements Chooser {
    private final EventHandlerStarter handler;
    private Alarm alarm;
    private final Sender messageSender;

    public AlarmEventDecorator(EventHandlerStarter handler, Alarm alarm,
                                                     Sender messageSender) {
        this.handler = handler;
        this.alarm = alarm;
        this.messageSender = messageSender;
    }

    @Override
    public void chooseEventProcessor(SensorEvent event) {
        if (alarm.isDeactivated()) {
            handler.chooseEventProcessor(event);
        } else if (alarm.isActivated()) {
            alarm.alert();
            messageSender.send("Alert! " + event.getClass().getName() + " happened");
        } else if (alarm.isAlerted()) {
            messageSender.send(event.getClass().getName() + " happened");
        }

    }
}
