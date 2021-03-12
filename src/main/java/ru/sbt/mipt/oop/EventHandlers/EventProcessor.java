package ru.sbt.mipt.oop.EventHandlers;


import ru.sbt.mipt.oop.Events.Event;
import ru.sbt.mipt.oop.SmartHome;

import java.util.List;

public class EventProcessor {

    List<EventHandler> eventHandlers;


    public EventProcessor(List eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    public void handleEvent(Event event) {
        System.out.println("Got event: " + event);
         eventHandlers.forEach(object -> {
                object.handleEvent(event);
            });

    }

}
