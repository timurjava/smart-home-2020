package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.Alarm.SMSSender;
import ru.sbt.mipt.oop.Alarm.Sender;
import ru.sbt.mipt.oop.EventHandlers.*;
import ru.sbt.mipt.oop.adapters.CoolCompanyAdapter;

import java.io.IOException;
import java.util.*;

public class Application {

    public static void main(String... args) throws IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }

}



