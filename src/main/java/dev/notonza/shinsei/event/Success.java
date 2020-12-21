package dev.notonza.shinsei.event;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
public class Success extends AbstractEvent {

    @Override
    public String getMessage() {
        return "Campaign assign successfully completed";
    }
}
