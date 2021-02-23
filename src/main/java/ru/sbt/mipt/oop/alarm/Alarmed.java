package ru.sbt.mipt.oop.alarm;

public class Alarmed implements AlarmStates {
    private final Alarm alarm;
    private final String code;

    public Alarmed(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void activate(String code) {

    }

    @Override
    public void deactivate(String code) {
        if (this.code.equals(code)) {
            alarm.changeState(new AlarmDeactivated(alarm));
        }
    }

    @Override
    public void alarm() {

    }
}
