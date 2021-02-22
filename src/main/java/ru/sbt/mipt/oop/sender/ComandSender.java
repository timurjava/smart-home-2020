package ru.sbt.mipt.oop.sender;

import ru.sbt.mipt.oop.SensorCommand;

public interface ComandSender {
    void sendCommand(SensorCommand command);
}
