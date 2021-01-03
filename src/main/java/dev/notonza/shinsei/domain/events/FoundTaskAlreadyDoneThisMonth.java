package dev.notonza.shinsei.domain.events;

import java.time.LocalDateTime;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
public class FoundTaskAlreadyDoneThisMonth extends AbstractEvent {

    private LocalDateTime latestSucceededAt;

    public FoundTaskAlreadyDoneThisMonth(LocalDateTime latestSucceededAt) {
        this.latestSucceededAt = latestSucceededAt;
    }

    @Override
    public String getMessage() {
        return "Campaign assign already done this month. Latest succeeded date time is [" + latestSucceededAt + "]";
    }
}