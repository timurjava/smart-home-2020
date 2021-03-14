package ru.sbt.mipt.oop;

import java.util.Arrays;

import static ru.sbt.mipt.oop.States.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.States.ALARM_DEACTIVATE;

public class SensorCommand {
    private final States type;
    private  String objectId;

    public SensorCommand(States type, String objectId) {
        if (Arrays.asList(ALARM_ACTIVATE, ALARM_DEACTIVATE).contains(type)) {
            throw new RuntimeException(new ClassCastException("Wrong type of alarmCode, should be int"));
        }
        this.type = type;
        this.objectId = objectId;
    }
    public SensorCommand(States type, int alarmCode) {
        if (!Arrays.asList(ALARM_ACTIVATE, ALARM_DEACTIVATE).contains(type)) {
            throw new RuntimeException(new ClassCastException("Wrong type of objectId, should be String"));
        }
        this.type = type;
    }

    @Override
    public String toString() {
        return "SensorCommand{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
