package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.Sender.ComandSenderintr;


public class HallEventHandler implements Handler{
    private SmartHome smartHome;
    private final ComandSenderintr comandSenderintr;

    public HallEventHandler(SmartHome smartHome, ComandSenderintr comandSenderintr){
        this.smartHome = smartHome;
        this.comandSenderintr = comandSenderintr;
    }

    @Override
    public void EventProcessing(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                if (room.getName().equals("hall")) {
                    for (Door door : room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            for (Room homeRoom : smartHome.getRooms()) {
                                for (Light light : homeRoom.getLights()) {
                                    light.setOn(false);
                                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                    comandSenderintr.sendCommand(command);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

