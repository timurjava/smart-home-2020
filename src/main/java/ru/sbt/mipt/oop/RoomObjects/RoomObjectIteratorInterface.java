package ru.sbt.mipt.oop.RoomObjects;

public interface RoomObjectIteratorInterface {
    boolean hasNext();

    RoomObject getNext();

    void reset();
}
