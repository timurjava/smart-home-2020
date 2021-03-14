package ru.sbt.mipt.oop.Events;

import ru.sbt.mipt.oop.States;

public class DoorEvent implements Event {
    private States type;
    private String objectId;

    public DoorEvent(String objectId, States type) {
        this.objectId = objectId;
        this.type = type;
    }

    @Override
    public States getState() {
        return type;
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
