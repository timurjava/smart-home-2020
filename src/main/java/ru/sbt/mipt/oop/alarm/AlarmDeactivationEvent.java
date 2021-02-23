package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.events.Event;

public class AlarmDeactivationEvent implements Event {
    private final String code;

    public AlarmDeactivationEvent(String code) {
        this.code = code;
    }

    String getCode() {
        return code;
    }
}

