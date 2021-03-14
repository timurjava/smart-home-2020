package ru.sbt.mipt.oop.Events;

import ru.sbt.mipt.oop.States;

public class AlarmEvent implements Event {
    private States type;
    private String code;
    private String objectId;

    public AlarmEvent(String objectId, States type, String code) {
        this.objectId = objectId;
        this.type = type;
        this.code = code;
    }

    public String getCode() {
        return code;
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
    public States getState() {
        return type;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + this.getType() +
                ", objectId='" + this.getObjectId() + '\'' +
                '}';
    }

}
