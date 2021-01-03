package dev.notonza.shinsei.usecase;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.notonza.shinsei.config.FormConfiguration;
import dev.notonza.shinsei.domain.events.*;
import dev.notonza.shinsei.domain.models.TaskResult;
import dev.notonza.shinsei.domain.models.complete_page.CompletePage;
import dev.notonza.shinsei.domain.models.complete_page.CompletePageSentenceNotSeenException;
import dev.notonza.shinsei.domain.models.confirm_page.ConfirmPage;
import dev.notonza.shinsei.domain.models.first_page.FirstPage;
import dev.notonza.shinsei.domain.models.JobExecutionHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.Async;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * @author gachakra
 * Created on 2020/12/07.
 */
@Singleton
@Slf4j
final public class TPointCampaignAssignUseCase {

    private FormConfiguration config;
    private JobExecutionHistoryRepository repository;

    @Inject
    public TPointCampaignAssignUseCase(FormConfiguration config,
                                       JobExecutionHistoryRepository repository) {
        this.config = config;
        this.repository = repository;
    }

    @Async.Execute
    public void handle() {
        LocalDateTime latestSucceededAt = repository.findLatestDateTimeByTaskResult(TaskResult.SUCCESS);
        if (YearMonth.from(latestSucceededAt).equals(YearMonth.now())) {
            log.info("Task had already done this month.");

            EventBus.getDefault().post(new FoundTaskAlreadyDoneThisMonth(latestSucceededAt));
            repository.persist(TaskResult.ALREADY_DONE_THIS_MONTH);
            return;
        }

        try {
            Connection.Response firstPageResponse = Jsoup.connect(config.site().url())
                .maxBodySize(0)
                .timeout(0)
                .method(Connection.Method.GET)
                .execute();

            Thread.sleep(500);
            Connection.Response confirmPageResponse = new FirstPage(firstPageResponse).submitForm(config);

            Thread.sleep(500);
            Connection.Response completePageResponse = new ConfirmPage(confirmPageResponse).submitForm(config);

            new CompletePage(completePageResponse).checkIfCompletionTextSeen();

            EventBus.getDefault().post(new Success());
            repository.persist(TaskResult.SUCCESS);

        } catch (CompletePageSentenceNotSeenException e) {
            log.error("Network Error", e);

            EventBus.getDefault().post(new CompletionNotConfirmed(e.actualPageSentence()));
            repository.persist(TaskResult.COMPLETE_PAGE_TEXT_NOT_SEEN);

            throw new RuntimeException(e);

        } catch (UnknownHostException e) {
            log.error("Network Error", e);

            EventBus.getDefault().post(new NetworkError());
            repository.persist(TaskResult.NETWORK_ERROR);

            throw new RuntimeException(e);

        } catch (Throwable e) {
            log.error("Critical Error", e);

            EventBus.getDefault().post(new SomethingWentWrong());
            repository.persist(TaskResult.CRITICAL_ERROR);

            throw new RuntimeException(e);
        }
    }
}
