package dev.notonza.shinsei.domain.events;

import java.time.LocalDateTime;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
public interface Event {

    String getMessage();

    LocalDateTime occurredOn();

    @Override
    String toString();
}
