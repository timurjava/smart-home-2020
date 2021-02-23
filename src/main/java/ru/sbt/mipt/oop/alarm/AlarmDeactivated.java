package ru.sbt.mipt.oop.alarm;

public class AlarmDeactivated implements AlarmStates {
    private final Alarm alarm;

    public AlarmDeactivated(Alarm alarm) {

        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.changeState(new AlarmActive(alarm, code));
    }

    @Override
    public void deactivate(String code) {

    }

    @Override
    public void alarm() {

    }
}
