package dev.notonza.shinsei.domain.models.complete_page;

import org.jsoup.Connection;

import java.io.IOException;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
public class CompletePage {

    private static final String TEXT_SHOULD_SEEN = "エントリー完了";

    private Connection.Response completePageResponse;

    public CompletePage(Connection.Response completePageResponse) {
        this.completePageResponse = completePageResponse;
    }

    public void checkIfCompletionTextSeen() throws IOException, CompletePageSentenceNotSeenException {
        String pageTitle = completePageResponse
            .parse()
            .select("title")
            .text();

        if (pageTitle.startsWith(TEXT_SHOULD_SEEN)) {
            return;
        }

        throw new CompletePageSentenceNotSeenException(pageTitle);
    }
}
