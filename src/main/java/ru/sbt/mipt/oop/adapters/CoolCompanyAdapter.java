package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.EventHandlers.EventHandlerMy;
import ru.sbt.mipt.oop.Events.DoorEvent;
import ru.sbt.mipt.oop.Events.Event;
import ru.sbt.mipt.oop.Events.LightEvent;
import ru.sbt.mipt.oop.States;

import java.util.Map;

public class CoolCompanyAdapter implements EventHandler {
    private final EventHandlerMy eventHandlerMy;
    private final Map<String, States> eventTransformation;


    public CoolCompanyAdapter(EventHandlerMy eventHandlerMy, Map<String, States> eventTransformation) {
        this.eventHandlerMy = eventHandlerMy;
        this.eventTransformation  = eventTransformation;
    }
    private Event eventTransformation(CCSensorEvent event){
        if(eventTransformation.containsKey(event.getEventType())){
            if(eventTransformation.get(event.getEventType()).equals(States.LIGHT_OFF) || eventTransformation.get(event.getEventType()).equals(States.LIGHT_ON)){
                return new LightEvent(event.getObjectId(),eventTransformation.get(event.getEventType()));
            }
            if(eventTransformation.get(event.getEventType()).equals(States.DOOR_CLOSED) || eventTransformation.get(event.getEventType()).equals(States.DOOR_OPEN)){
                return new DoorEvent(event.getObjectId(),eventTransformation.get(event.getEventType()));
            }
        }
        return null;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        Event transformedEvent = eventTransformation(event);
        if(transformedEvent != null){
            eventHandlerMy.handleEvent(transformedEvent);
        }
    }
}
