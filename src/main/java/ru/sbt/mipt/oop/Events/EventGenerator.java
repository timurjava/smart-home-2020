package ru.sbt.mipt.oop.Events;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventGenerator {
     SensorEvent getNextEvent();
}
