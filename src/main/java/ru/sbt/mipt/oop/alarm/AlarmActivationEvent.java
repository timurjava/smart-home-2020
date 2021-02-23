package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.events.Event;

public class AlarmActivationEvent implements Event {
    private final String code;

    public AlarmActivationEvent(String code) {
        this.code = code;
    }

    String getCode() {
        return code;
    }
}
