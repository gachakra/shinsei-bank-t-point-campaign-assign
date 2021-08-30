package dev.notonza.shinsei.application;

import com.google.inject.Inject;
import dev.notonza.shinsei.domain.events.*;
import dev.notonza.shinsei.application.notification.SlackReporter;
import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.Async;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
@Slf4j
public class EventSubscriber {

    private SlackReporter slackReporter;

    @Inject
    public EventSubscriber(SlackReporter slackReporter) {
        this.slackReporter = slackReporter;
    }

    @Async.Schedule
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onSuccess(Success event) {
        log.info(event.toString());
        slackReporter.postToChannel(event.getMessage());
    }

    @Async.Schedule
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onFoundTaskAlreadyDoneThisMonth(FoundTaskAlreadyDoneThisMonth event) {
        log.warn(event.toString());
        slackReporter.postToChannel(event.getMessage());
    }

    @Async.Schedule
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onCompletionNotConfirmed(CompletionNotConfirmed event) {
        log.error(event.toString());
        slackReporter.postToChannel(event.getMessage());
    }

    @Async.Schedule
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onNetworkError(NetworkError event) {
        log.error(event.toString());
        slackReporter.postToChannel(event.getMessage());
    }

    @Async.Schedule
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onSomethingWentWrong(SomethingWentWrong event) {
        log.error(event.toString());
        slackReporter.postToChannel(event.getMessage());
    }
}
