package ru.sbt.mipt.oop.sender;

import ru.sbt.mipt.oop.SensorCommand;

public interface Sender {
    void send(String command);
}
