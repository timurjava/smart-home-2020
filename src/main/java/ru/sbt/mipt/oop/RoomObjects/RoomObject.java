package ru.sbt.mipt.oop.RoomObjects;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.States;

public class RoomObject implements RoomObjectInterface, Actionable {
    final String id;

    public RoomObject(String id) {
        this.id = id;
    }

    public void setState(States mean) {
    }

    public void setHome(SmartHome home) {
    }

    public String getString() {
        return "";
    }

    public String getId() {
        return "";
    }


    @Override
    public void execute(Action action) {
    }
}
