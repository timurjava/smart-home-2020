package ru.sbt.mipt.oop.events;

public class SensorEventGenerator implements EventGenerator {
    @Override
    public SensorEvent getNextEvent() {
        if (Math.random() < 0.05) return null; // null means end of event stream
        EventType eventType = EventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(eventType, objectId);
    }
}
