package ru.sbt.mipt.oop.Events;

import ru.sbt.mipt.oop.States;

public interface Event {


    String getObjectId();

    String getType();

    States getState();

}
