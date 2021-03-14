package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.RoomObjects.Door;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.States;
import ru.sbt.mipt.oop.command.senders.CommandSender;

public class CloseHallDoorCommand implements Command {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public CloseHallDoorCommand(SmartHome smartHome, CommandSender commandSender) {
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
            if (!(room.getName().equals("hall"))) {
                return;
            }
            smartHome.execute(doorInstance -> {
                if (!(doorInstance instanceof Door)) {
                    return;
                }
                Door door = (Door) doorInstance;
                door.setState(States.DOOR_CLOSED);
                System.out.println("Door " + door.getId() + " was closed.");
                SensorCommand sensorCommand = new SensorCommand(States.DOOR_CLOSED, door.getId());
                commandSender.sendCommand(sensorCommand);
            });
        });
    }
}
