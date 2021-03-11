package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Events.*;

class EventGenerator implements EventGeneratorInterface {
    public Event generateEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        String objectId = "" + ((int) (10 * Math.random()));
        Event[] events = {new DoorEvent(objectId, TypeEvent.DOOR_CLOSE), new DoorEvent(objectId, TypeEvent.DOOR_OPEN),
                new LightEvent(objectId, TypeEvent.LIGHT_OFF), new LightEvent(objectId, TypeEvent.LIGHT_ON),
                new AlarmEvent("alarm", TypeEvent.ALARM_ACTIVATE, "1234"),
                new AlarmEvent("alarm", TypeEvent.ALARM_DEACTIVATE, "12345"),
                new AlarmEvent("alarm", TypeEvent.ALARM_ACTIVATE, "123"),
                new AlarmEvent("alarm", TypeEvent.ALARM_DEACTIVATE, "1235"),};
        Event event = events[(int) (6 * Math.random())];

        return event;
    }
}
