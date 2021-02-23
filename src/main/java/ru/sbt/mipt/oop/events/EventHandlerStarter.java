package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Chooser;
import ru.sbt.mipt.oop.handlers.Handler;

import java.util.ArrayList;
import java.util.Collection;

public class EventHandlerStarter implements Chooser {
    private Collection<Handler> eventProcessor = new ArrayList<>();

    public EventHandlerStarter(Collection<Handler> eventProcessor) {
        this.eventProcessor = eventProcessor;
    }
    /**
     *
     * @param event
     * принимает событие и дом, в котором работает, создает коллекцию обработчиков и прокидывает через нее событие
     */
    @Override
    public void chooseEventProcessor(SensorEvent event) {
        for (Handler processor: eventProcessor) {
            processor.EventProcessing(event);
        }
    }
}
