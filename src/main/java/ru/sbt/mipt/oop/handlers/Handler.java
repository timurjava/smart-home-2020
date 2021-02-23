package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface Handler {
     void EventProcessing(SensorEvent event);
}
