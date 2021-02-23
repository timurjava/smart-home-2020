package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.handlers.Handler;
import ru.sbt.mipt.oop.sender.Sender;

public class AlarmEventDecorator implements Handler {
    private final Handler handler;
    private Alarm alarm;
    private final Sender messageSender;

    public AlarmEventDecorator(Handler handler, Alarm alarm,
                                                     Sender messageSender) {
        this.handler = handler;
        this.alarm = alarm;
        this.messageSender = messageSender;
    }

    @Override
    public void EventProcessing(Event event) {
        if (alarm.isDeactivated()) {
            handler.EventProcessing(event);
        } else if (alarm.isActivated()) {
            alarm.alert();
            messageSender.send("Alert! " + event.getClass().getName() + " happened");
        } else if (alarm.isAlerted()) {
            messageSender.send(event.getClass().getName() + " happened");
        }

    }
}
