package ru.sbt.mipt.oop.Alarm;

public class AlarmActivated implements AlarmState {
    Alarm alarm;

    public AlarmActivated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate() {
        System.out.println("Try activate activated alarm");
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Deactivate activated alarm with code " + code);
        if (alarm.checkCode(code)) {
            alarm.setState(new AlarmDeactivated(alarm));
        } else {
            alarm.setState(new AlarmActiveState(alarm));
            alarm.danger();
        }
    }

    @Override
    public void danger() {
        alarm.setState(new AlarmActiveState(alarm));
    }

}
