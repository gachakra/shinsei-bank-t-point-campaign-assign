package dev.notonza.shinsei.event;

/**
 * @author gachakra
 * Created on 2020/12/10.
 */
public class SomethingWentWrong extends AbstractEvent {

    @Override
    public String getMessage() {
        return "Something went wrong while campaign assign";
    }
}