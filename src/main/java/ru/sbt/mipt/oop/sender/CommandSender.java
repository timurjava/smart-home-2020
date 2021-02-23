package ru.sbt.mipt.oop.sender;

import ru.sbt.mipt.oop.SensorCommand;

public class CommandSender implements Sender {
    public  void send(String command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
