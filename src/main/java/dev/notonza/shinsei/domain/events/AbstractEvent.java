package dev.notonza.shinsei.domain.events;

import java.time.LocalDateTime;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
public abstract class AbstractEvent implements Event {

    protected LocalDateTime occurredOn;

    protected AbstractEvent() {
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {

        return String.format("Event [%s] published. occurredAt: [%s], message: [%s]",
            getClass().getSimpleName(),
            occurredOn(),
            getMessage());
    }
}
