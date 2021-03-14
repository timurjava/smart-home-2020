package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SmartHome;

public class Alarm {
    private AlarmState state;
    private final int timeSleep;
    private final String id;
    private final String code;


    public Alarm (String code) {
        this.id = "id";
        this.timeSleep = 5;
        this.state = new AlarmDeactivated(this);
        this.code = code;
    }

    public Alarm( int timeSleep, String id, String code) {
        this.timeSleep = timeSleep;
        this.id = id;
        this.state = new AlarmDeactivated(this);
        this.code = code;
    }

    public boolean isActivated(){
        return this.getState() instanceof AlarmActivated ;
    }
    public void trigger(){
        this.setState(new AlarmActiveState(this));
    }
    public boolean isDeactivated(){
        return  this.getState() instanceof AlarmDeactivated;
    }
    public boolean isInDanger(){
        return this.getState() instanceof AlarmActiveState;
    }
    public void activate() {
        state.activate();
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    void setState(AlarmState state) {
        this.state = state;
    }

    public void danger() {
        state.danger();
    }

    public boolean checkCode(String code) {
        return code.equals(this.code);
    }

    public void execute(Action action) {
        action.execute(this);
    }

    public AlarmState getState() {
        return state;
    }


    private class AlarmDeactivated implements AlarmState {
        Alarm alarm;

        public AlarmDeactivated(Alarm alarm) {
            this.alarm = alarm;
        }

        @Override
        public void activate() {
            System.out.println("Activate alarm");
            alarm.setState(new AlarmActivated(alarm));
        }

        @Override
        public void deactivate(String code) {
            System.out.println("Try deactivate deactivated alarm");
        }

        @Override
        public void danger() {
            alarm.setState(new AlarmActiveState(alarm));
        }

    }

    private class AlarmActiveState implements AlarmState {
        Alarm alarm;

        public AlarmActiveState(Alarm alarm) {
            this.alarm = alarm;
        }

        @Override
        public void activate() {
            System.out.println("Try activate Alarm in Active State");
        }

        @Override
        public void deactivate(String code) {
            if (alarm.checkCode(code)) {
                System.out.println("Deactivate ActiveState with right code");
                alarm.setState(new AlarmDeactivated(alarm));
            } else {
                System.out.println("Wrong code");
            }
        }

        @Override
        public void danger() {
        }

    }

    private class AlarmActivated implements AlarmState {
        private final Alarm alarm;

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

}
