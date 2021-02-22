package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Events.EventHandlerChooser;
import ru.sbt.mipt.oop.Events.SensorEventGenerator;
import ru.sbt.mipt.oop.Reader.SmartHomeReaderJson;
import ru.sbt.mipt.oop.Sender.CommandSender;
import ru.sbt.mipt.oop.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.handlers.HallEventHandler;
import ru.sbt.mipt.oop.handlers.Handler;
import ru.sbt.mipt.oop.handlers.LightEventHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args)  {
        SmartHomeReaderJson reader = new SmartHomeReaderJson();
        SmartHome smartHome = reader.read("smart-home-1.js");
        SensorEventGenerator sensorEventGenerator = new SensorEventGenerator();
        SensorEvent event = sensorEventGenerator.getNextEvent();
        EventHandlerChooser eventHandlerChooser = new EventHandlerChooser(sensorEventGenerator);
        List<Handler> handlers = Arrays.asList(new LightEventHandler(smartHome),new HallEventHandler(smartHome,
                new CommandSender()), new DoorEventHandler(smartHome));
        eventHandlerChooser.choosehandler(smartHome, event, handlers);
    }

}
