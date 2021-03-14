package ru.sbt.mipt.oop.EventHandlers;

import org.junit.Assert;
import ru.sbt.mipt.oop.Alarm.SMSSender;
import ru.sbt.mipt.oop.Alarm.Sender;
import ru.sbt.mipt.oop.Events.*;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.RoomObjects.Door;
import ru.sbt.mipt.oop.RoomObjects.Light;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.States;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class EventProcessorTest {

    Light light1 = new Light("1", "LIGHT_OFF");
    Sender sender = new SMSSender();
    Light light2 = new Light("2", "LIGHT_ON");
    Door door1 = new Door("3", "DOOR_OPEN");
    List<Door> doors = Arrays.asList(door1);
    List<Light> lights = Arrays.asList(light1, light2);
    Room room = new Room(lights, doors, "hall");
    Event event2 = new DoorEvent("3", States.DOOR_CLOSED);
    Event event1 = new LightEvent("1", States.LIGHT_ON);
    List<Room> roomList = Arrays.asList(room);
    SmartHome smartHome = new SmartHome(roomList);
    EventProcessor eventProcessor = new EventProcessor(new ArrayList<EventHandlerMy>(Arrays.asList(new SecurityProcessorDecorator(new LightEventHandlerMy(smartHome), sender, smartHome ),
            new SecurityProcessorDecorator(new DoorEventHandlerMy(smartHome), sender, smartHome ),
            new SecurityProcessorDecorator(new HallDoorEventHandlerMy(smartHome), sender, smartHome ),
            new AlarmEventHandlerMy(smartHome))));


    @org.junit.jupiter.api.Test
    void handleEvent() {
        eventProcessor.handleEvent(event1);
        Assert.assertEquals(light1.getState(), States.LIGHT_ON);
        eventProcessor.handleEvent(event2);
        Assert.assertEquals(door1.getState(), States.DOOR_CLOSED);
        Assert.assertEquals(light1.getState(), States.LIGHT_OFF);
        Assert.assertEquals(light2.getState(), States.LIGHT_OFF);
    }
}