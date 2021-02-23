package ru.sbt.mipt.oop.alarm;

public class Alarm {
    private AlarmStates state;

    public Alarm() {
        state = new AlarmDeactivated(this);
    }

    void changeState(AlarmStates newState) {
        state = newState;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void alert() {
        state.alarm();
    }

    public boolean isDeactivated() {
        return state instanceof AlarmDeactivated;
    }

    public boolean isActivated() {
        return state instanceof AlarmActive;
    }

    public boolean isAlerted() {
        return state instanceof Alarmed;
    }
}
