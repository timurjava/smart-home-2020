package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.Alarm.SMSSender;
import ru.sbt.mipt.oop.Alarm.Sender;
import ru.sbt.mipt.oop.EventHandlers.*;
import ru.sbt.mipt.oop.adapters.CoolCompanyAdapter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SpringConfig{
    @Bean
    public Map<String, States> transformMap() {
        return new HashMap<String, States>() {{
            put("LightIsOn", States.LIGHT_ON);
            put("LightIsOff", States.LIGHT_OFF);
            put("DoorIsOpen", States.DOOR_OPEN);
            put("DoorIsClosed", States.DOOR_CLOSED);
        }};
    }

    @Bean
    public SmartHomeProvider homeLoader() {
        return new JsonSmartHomeStateProvider("smart-home-1.js");
    }

    @Bean
    public SmartHome smartHome() {
        SmartHome smartHome = homeLoader().provideSmartHome();
        return smartHome;
    }

    @Bean
    public Sender messageSender() {
        return new SMSSender();
    }


    @Bean
    EventHandlerMy alarmEventHandler() {
        return new AlarmEventHandlerMy(smartHome());
    }

    @Bean
    EventHandlerMy doorEventHandler() {
        return new DoorEventHandlerMy(smartHome());
    }

    @Bean
    EventHandlerMy hallDoorEventHandler() {
        return new HallDoorEventHandlerMy(smartHome());
    }

    @Bean
    EventHandlerMy lightEventHandler() {
        return new LightEventHandlerMy(smartHome());
    }

    @Bean
    @Autowired
    public SensorEventsManager sensorEventsManager(List<EventHandlerMy> eventProcessors, SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        for (EventHandlerMy processor : eventProcessors) {
            sensorEventsManager.registerEventHandler(
                    new CoolCompanyAdapter(
                            new SecurityProcessorDecorator(processor, messageSender(), smartHome),
                            transformMap()
                    )
            );
        }
        return sensorEventsManager;
    }
}