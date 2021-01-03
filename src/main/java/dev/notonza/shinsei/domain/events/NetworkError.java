package dev.notonza.shinsei.domain.events;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
final public class NetworkError extends AbstractEvent {

    @Override
    public String getMessage() {
        return "Network error occurred";
    }
}
