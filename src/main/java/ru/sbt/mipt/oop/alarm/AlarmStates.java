package ru.sbt.mipt.oop.alarm;

public interface AlarmStates {
    void activate(String code);
    void deactivate(String code);
    void alarm();
}
