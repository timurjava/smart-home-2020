package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventHandlerChooser;
import ru.sbt.mipt.oop.events.SensorEventGenerator;
import ru.sbt.mipt.oop.reader.SmartHomeReaderJson;
import ru.sbt.mipt.oop.sender.CommandSender;
import ru.sbt.mipt.oop.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.handlers.HallEventHandler;
import ru.sbt.mipt.oop.handlers.Handler;
import ru.sbt.mipt.oop.handlers.LightEventHandler;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args)  {
        SmartHomeReaderJson reader = new SmartHomeReaderJson();
        SmartHome smartHome = reader.read("smart-home-1.js");
        SensorEventGenerator sensorEventGenerator = new SensorEventGenerator();
        SensorEvent event = sensorEventGenerator.getNextEvent();
        EventHandlerChooser eventHandlerChooser = new EventHandlerChooser(sensorEventGenerator);
        CommandSender commandSender = new CommandSender();
        List<Handler> handlers = Arrays.asList(new LightEventHandler(smartHome),new HallEventHandler(smartHome,
                commandSender), new DoorEventHandler(smartHome));
        eventHandlerChooser.choosehandler(smartHome, event, handlers);
    }

}
