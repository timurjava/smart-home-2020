package ru.sbt.mipt.oop;

import java.util.Iterator;

public class RoomIterator implements Iterator {
    private SmartHome smartHome;
    private Iterator<Room> roomIterator;

    public RoomIterator(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.roomIterator = smartHome.getRooms().iterator();
    }

    @Override
    public boolean hasNext() {
        return roomIterator.hasNext();
    }

    @Override
    public Room next() {
        if (roomIterator.hasNext())
            return roomIterator.next();
        return null;
    }
}
