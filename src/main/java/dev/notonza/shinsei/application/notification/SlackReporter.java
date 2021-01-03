package dev.notonza.shinsei.application.notification;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import dev.notonza.shinsei.config.SlackConfiguration;
import dev.notonza.shinsei.shared.Time;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
@Singleton
@Slf4j
public class SlackReporter {

    private static final Time RETRY_DURATION = new Time(1, TimeUnit.HOURS);
    private static final int RETRY_LIMIT_COUNT = 6;

    private final String botToken;
    private final String channelId;

    @Inject
    public SlackReporter(SlackConfiguration config) {
        botToken = config.botToken();
        channelId = config.channelId();
    }

    public void postToChannel(String message) {
        if (Strings.isNullOrEmpty(botToken) || Strings.isNullOrEmpty(channelId)) {
            log.warn("Some Slack configurations might be empty. Reporting is not executed.");
            return;
        }

        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods(botToken);

        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
            .channel(channelId)
            .text(message)
            .build();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        AtomicInteger executedCount = new AtomicInteger(1);

        scheduler.scheduleWithFixedDelay(() -> {

            int currentAttempts = executedCount.getAndIncrement();
            if (currentAttempts > RETRY_LIMIT_COUNT) {
                log.error("Slack reporting retried " + RETRY_LIMIT_COUNT + " times. Scheduler is shutting down.");
                scheduler.shutdown();
                Thread.currentThread().interrupt();
                return;
            }

            try {
                ChatPostMessageResponse response = methods.chatPostMessage(request);
                if (response.isOk()) {
                    log.info("Slack reporting succeeded. message: [" + message + "]");
                    scheduler.shutdown();
                    Thread.currentThread().interrupt();
                    return;
                }

                throw new SlackReportingFailedException(response.getMessage().toString());

            } catch (Throwable e) {
                log.error("Slack reporting failed. attempt(s): " + new AtomicInteger(currentAttempts).addAndGet(1), e);
                log.warn("Slack reporting retrying...");
            }

        }, 0, RETRY_DURATION.value(), RETRY_DURATION.timeUnit());
    }
}