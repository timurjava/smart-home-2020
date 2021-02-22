package ru.sbt.mipt.oop.sender;

import ru.sbt.mipt.oop.SensorCommand;

public class CommandSender implements ComandSender {
    public  void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
