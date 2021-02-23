package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface Chooser {
    public void chooseEventProcessor(SensorEvent event);
}
