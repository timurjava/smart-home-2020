package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Light;

public class TurnOffAllTheLights implements Action {
    @Override
    public void doAction(Object object) {
        if(object instanceof Light){
            ((Light) object).setOn(false);
        }
    }
}
