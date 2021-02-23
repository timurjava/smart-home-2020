package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.handlers.Handler;

import java.util.List;


public class EventHandlerChooser {
    private final EventGenerator eventGenerator;

    public EventHandlerChooser(EventGenerator eventGenerator) {
        this.eventGenerator = eventGenerator;
    }

    public void choosehandler(SmartHome smartHome, SensorEvent event, List<Handler> handlers){
        while (event != null) {
            System.out.println("Got event: " + event);
            for (Handler handler:handlers) {
                handler.EventProcessing(event);
            }
            event = eventGenerator.getNextEvent();

        }
    }
}

