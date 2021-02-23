package ru.sbt.mipt.oop.events;

public class SensorEvent  {
    private final EventType type;
    private final String objectId;
    private String code;


    public SensorEvent(EventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
        this.code = code;

    }

    public EventType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }


    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
