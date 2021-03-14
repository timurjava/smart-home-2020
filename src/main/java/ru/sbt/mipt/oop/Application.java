package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.Alarm.SMSSender;
import ru.sbt.mipt.oop.Alarm.Sender;
import ru.sbt.mipt.oop.EventHandlers.*;
import ru.sbt.mipt.oop.adapters.CoolCompanyAdapter;

import java.io.IOException;
import java.util.*;

public class Application {

    public static void main(String... args) throws IOException {
        String path = "smart-home-1.js";
        // считываем состояние дома из файла
        JsonSmartHomeStateProvider json = new JsonSmartHomeStateProvider(path);
        SmartHome smartHome = json.provideSmartHome();
        Sender sender = new SMSSender();
        List<EventHandlerMy> eventProcessors = new ArrayList<>(Arrays.asList(
                new DoorEventHandlerMy(smartHome),
                new LightEventHandlerMy(smartHome),
                new HallDoorEventHandlerMy(smartHome)));
        Map<String, States> testmap = new HashMap<>();
        testmap.put("LightIsOn", States.LIGHT_ON);
        testmap.put("LightIsOff", States.LIGHT_OFF);
        testmap.put("DoorIsOpen", States.DOOR_OPEN);
        testmap.put("DoorIsClosed", States.DOOR_CLOSED);
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        for (EventHandlerMy processor : eventProcessors) {
            sensorEventsManager.registerEventHandler(
                    new CoolCompanyAdapter(new SecurityProcessorDecorator(processor, sender, smartHome), testmap));
        }
        sensorEventsManager.start();
    }

}



