package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Alarm.SMSSender;
import ru.sbt.mipt.oop.Alarm.Sender;
import ru.sbt.mipt.oop.EventHandlers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        String path = "smart-home-1.js";
        // считываем состояние дома из файла
        JsonSmartHomeStateProvider json = new JsonSmartHomeStateProvider(path);
        SmartHome smartHome = json.provideSmartHome();
        Sender sender = new SMSSender();
        List<EventHandler> eventHandlers = new ArrayList<EventHandler>(Arrays.asList(new SecurityProcessorDecorator(new LightEventHandler(smartHome), sender, smartHome ),
                new SecurityProcessorDecorator(new DoorEventHandler(smartHome), sender, smartHome ),
                new SecurityProcessorDecorator(new HallDoorEventHandler(smartHome), sender, smartHome ),
                new AlarmEventHandler(smartHome)));


        EventProcessor eventProcessorMy = new EventProcessor(eventHandlers);
        EventGenerator eventGenerator = new EventGenerator();
        // начинаем цикл обработки событий
        SmartHomeManager smartHomeManager = new SmartHomeManager(smartHome, eventProcessorMy, eventGenerator);
        smartHomeManager.startTrackingEvents();
    }


}
