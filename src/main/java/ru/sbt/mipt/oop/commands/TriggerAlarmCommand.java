package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.States;
import ru.sbt.mipt.oop.command.senders.CommandSender;

public class TriggerAlarmCommand implements Command {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public TriggerAlarmCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().trigger();
        SensorCommand sensorCommand = new SensorCommand(States.ALARM_ACTIVATE, 12345);
        commandSender.sendCommand(sensorCommand);
    }
}