package dev.notonza.shinsei.domain.events;

/**
 * @author gachakra
 * Created on 2020/12/10.
 */
public class CompletionNotConfirmed extends AbstractEvent {

    private String actualPageSentence;

    public CompletionNotConfirmed(String actualPageSentence) {
        this.actualPageSentence = actualPageSentence;
    }

    @Override
    public String getMessage() {
        return "Actual page sentence seen was [" + actualPageSentence + "]";
    }
}