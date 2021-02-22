package ru.sbt.mipt.oop.Sender;

import ru.sbt.mipt.oop.Sender.ComandSenderintr;
import ru.sbt.mipt.oop.SensorCommand;

public class CommandSender implements ComandSenderintr {
    public  void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
