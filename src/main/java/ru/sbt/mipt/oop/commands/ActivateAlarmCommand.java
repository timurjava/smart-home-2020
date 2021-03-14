package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.States;
import ru.sbt.mipt.oop.command.senders.CommandSender;


public class ActivateAlarmCommand implements Command {
    private final SmartHome smartHome;
    private final CommandSender commandSender;
    private final int code;

    public ActivateAlarmCommand(SmartHome smartHome, CommandSender commandSender, int code) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
        this.code = code;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate();
        SensorCommand sensorCommand = new SensorCommand(States.ALARM_ACTIVATE, code);
        commandSender.sendCommand(sensorCommand);
    }
}
