package dev.notonza.shinsei.domain.complete_page;

import com.google.common.base.Strings;

/**
 * @author gachakra
 * Created on 2020/12/10.
 */
public class CompletePageSentenceNotSeenException extends RuntimeException {

    private final String actualPageSentence;

    public CompletePageSentenceNotSeenException(String actualPageSentence) {

        super(Strings.isNullOrEmpty(actualPageSentence)
            ? "Page sentence was empty"
            : "Page sentence was [" + actualPageSentence + "]");

        this.actualPageSentence = actualPageSentence;
    }

    public String actualPageSentence() {
        return actualPageSentence;
    }
}
