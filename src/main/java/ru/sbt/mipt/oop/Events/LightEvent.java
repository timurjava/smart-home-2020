package ru.sbt.mipt.oop.Events;

import ru.sbt.mipt.oop.States;

public class LightEvent implements Event {
    private LightTypeEvent type;
    private String objectId;

    public LightEvent(String objectId, LightTypeEvent type) {
        this.objectId = objectId;
        this.type = type;
    }

    @Override
    public States getState() {
        return type.getState();
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    @Override
    public String getType() {
        return type.toString();
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + this.getType() +
                ", objectId='" + this.getObjectId() + '\'' +
                '}';
    }

}
