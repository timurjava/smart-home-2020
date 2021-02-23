package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Chooser;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmEventDecorator;
import ru.sbt.mipt.oop.handlers.Handler;
import ru.sbt.mipt.oop.sender.Sender;

import java.util.List;


public class EventHandlerChooser {
    private final EventGenerator eventGenerator;

    public EventHandlerChooser(EventGenerator eventGenerator) {
        this.eventGenerator = eventGenerator;
    }

    public void choosehandler(Alarm alarm, SensorEvent event, List<Handler> handlers, Sender someSender){
        while (event != null) {
            System.out.println("Got event: " + event);
            Chooser chooser = new AlarmEventDecorator(new EventHandlerStarter(handlers), alarm, someSender);
            chooser.chooseEventProcessor(event);
            event = eventGenerator.getNextEvent();

        }
    }
}

