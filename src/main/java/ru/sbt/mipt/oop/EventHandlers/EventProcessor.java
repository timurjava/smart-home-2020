package ru.sbt.mipt.oop.EventHandlers;


import ru.sbt.mipt.oop.Events.Event;

import java.util.List;

public class EventProcessor {

    List<EventHandlerMy> eventHandlerMIES;


    public EventProcessor(List eventHandlers) {
        this.eventHandlerMIES = eventHandlers;
    }

    public void handleEvent(Event event) {
        System.out.println("Got event: " + event);
         eventHandlerMIES.forEach(object -> {
                object.handleEvent(event);
            });

    }

}
