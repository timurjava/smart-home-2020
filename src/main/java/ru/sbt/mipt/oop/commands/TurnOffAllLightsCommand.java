package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.RoomObjects.Light;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.States;
import ru.sbt.mipt.oop.command.senders.CommandSender;

public class TurnOffAllLightsCommand implements Command {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public TurnOffAllLightsCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.execute(lightInstance -> {
            if (!(lightInstance instanceof Light)) {
                return;
            }
            Light light = (Light) lightInstance;
            light.setState(States.LIGHT_OFF);
            System.out.println("Light " + light.getId() + " was turned off.");
            SensorCommand sensorCommand = new SensorCommand(States.LIGHT_OFF, light.getId());
            commandSender.sendCommand(sensorCommand);
        });
    }
}
