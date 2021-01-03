package dev.notonza.shinsei;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.notonza.shinsei.application.EventSubscriber;
import dev.notonza.shinsei.shared.Time;
import dev.notonza.shinsei.usecase.TPointCampaignAssignUseCase;
import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gachakra
 * Created on 2020/12/02.
 */
@Slf4j
final public class ShinseiBankTPointCampaignAssignApplication {

    private static final Injector injector = Guice
        .createInjector(new ShinseiBankTPointCampaignAssignApplicationModule());


    private static final Time RETRY_DURATION = new Time(1, TimeUnit.HOURS);
    private static final int RETRY_LIMIT_COUNT = 6;

    public static void main(String[] args) {

        EventBus.getDefault().register(injector.getInstance(EventSubscriber.class));

        TPointCampaignAssignUseCase useCase = injector.getInstance(TPointCampaignAssignUseCase.class);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        AtomicInteger executedCount = new AtomicInteger(1);

        scheduler.scheduleWithFixedDelay(() -> {

            if (executedCount.getAndIncrement() > RETRY_LIMIT_COUNT) {
                log.error("Campaign assigning retried " + RETRY_LIMIT_COUNT + " times. System is closing.");

                scheduler.shutdown();
                return;
            }

            try {
                useCase.handle();
                log.info("Campaign assigning Done.");

                scheduler.shutdown();
                return;

            } catch (Throwable e) {
                log.warn("Will retry after " + RETRY_DURATION.toString() + " cause of " + e.getClass().getName());
            }

        }, 0, RETRY_DURATION.value(), RETRY_DURATION.timeUnit());
    }
}