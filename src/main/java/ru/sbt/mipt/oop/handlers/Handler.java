package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;

public interface Handler {
     void EventProcessing(SensorEvent event);
}
