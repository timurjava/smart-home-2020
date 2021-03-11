package ru.sbt.mipt.oop.RoomObjects;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Iterator;

public class LightIterator implements RoomObjectIteratorInterface {
    private SmartHome smartHome;
    private Iterator<Room> roomIterator;
    private Iterator<Light> lightIterator;
    private Room currentRoom;
    private Light currentLight;

    LightIterator(SmartHome smartHome) {
        this.smartHome = smartHome;
        roomIterator = smartHome.getRooms().iterator();
        currentRoom = null;
        lightIterator = null;
        currentLight = null;
    }

    @Override
    public boolean hasNext() {
        return (roomIterator.hasNext()) || (lightIterator.hasNext());
    }

    @Override
    public Light getNext() {
        if (hasNext()) {
            if ((lightIterator != null) && (lightIterator.hasNext())) {
                currentLight = lightIterator.next();
                return currentLight;
            } else {
                currentRoom = roomIterator.next();
                lightIterator = currentRoom.getLights().iterator();
                currentLight = lightIterator.next();
                return currentLight;
            }
        }
        return null;
    }

    @Override
    public void reset() {
        roomIterator = smartHome.getRooms().iterator();
        currentRoom = null;
        lightIterator = null;
        currentLight = null;
    }
}
