package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.Alarm.SMSSender;
import ru.sbt.mipt.oop.Alarm.Sender;
import ru.sbt.mipt.oop.EventHandlers.*;
import ru.sbt.mipt.oop.adapters.CoolCompanyAdapter;
import ru.sbt.mipt.oop.command.senders.CommandSender;
import ru.sbt.mipt.oop.command.senders.PretendingCommandSender;
import ru.sbt.mipt.oop.commands.*;
import ru.sbt.mipt.oop.remote.control.SmartHomeRemoteControl;


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

    @Bean
    CommandSender commandSender() {
        return new PretendingCommandSender();
    }

    @Bean
    public Command turnOnAllLightsCommand() {
        return new TurnOnAllLightsCommand(smartHome(), commandSender());
    }

    @Bean
    public Command turnOffAllLightsCommand() {
        return new TurnOffAllLightsCommand(smartHome(), commandSender());
    }

    @Bean
    public Command turnOnHallLightsCommand() {
        return new TurnOnHallLightsCommand(smartHome(), commandSender());
    }

    @Bean
    public Command closeHallDoorCommand() {
        return new CloseHallDoorCommand(smartHome(), commandSender());
    }

    @Bean
    public Command activateAlarmCommand() {
        return new ActivateAlarmCommand(smartHome(), commandSender(), 12345);
    }

    @Bean
    public Command activateAlertModeCommand() {
        return new TriggerAlarmCommand(smartHome(), commandSender());
    }

    @Bean
    public Map<String, Command> buttonsToCommands() {
        Map<String, Command> buttonsToCommands = new HashMap<>();
        buttonsToCommands.put("A", activateAlarmCommand());
        buttonsToCommands.put("B", activateAlertModeCommand());
        buttonsToCommands.put("C", closeHallDoorCommand());
        buttonsToCommands.put("D", turnOffAllLightsCommand());
        buttonsToCommands.put("1", turnOnAllLightsCommand());
        buttonsToCommands.put("2", turnOnHallLightsCommand());
        return buttonsToCommands;
    }

    @Bean
    public SmartHomeRemoteControl remoteControl() {
        return new SmartHomeRemoteControl(buttonsToCommands(), "1");
    }

    @Bean
    @Autowired
    public RemoteControlRegistry remoteControlRegistry(List<SmartHomeRemoteControl> remoteControls) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControls.forEach(remoteControl -> remoteControlRegistry.registerRemoteControl(remoteControl,
                remoteControl.getRcId()));
        return new RemoteControlRegistry();
    }
}