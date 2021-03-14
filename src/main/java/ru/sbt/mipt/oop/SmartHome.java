package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
        alarm = new Alarm("code");
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        alarm = new Alarm("code");

    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }


    public Alarm getAlarm() {
        return alarm;
    }


    @Override
    public void execute(Action action) {
        rooms.forEach(room -> {
            room.execute(action);
        });
        alarm.execute(action);
        action.execute(this);
    }

}
