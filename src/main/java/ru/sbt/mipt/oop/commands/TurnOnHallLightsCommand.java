package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.RoomObjects.Light;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.States;
import ru.sbt.mipt.oop.command.senders.CommandSender;


public class TurnOnHallLightsCommand implements Command {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public TurnOnHallLightsCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.execute(roomInstance -> {
            if (!(roomInstance instanceof Room)) {
                return;
            }
            Room room = (Room) roomInstance;
            if (!room.getName().equals("hall")) {
                return;
            }
            room.execute(lightInstance -> {
                if (!(lightInstance instanceof Light)) {
                    return;
                }
                Light light = (Light) lightInstance;
                light.setState(States.LIGHT_ON);
                System.out.println("Light " + light.getId() + " was turned on.");
                SensorCommand sensorCommand = new SensorCommand(States.LIGHT_ON, light.getId());
                commandSender.sendCommand(sensorCommand);
            });
        });
    }
}

