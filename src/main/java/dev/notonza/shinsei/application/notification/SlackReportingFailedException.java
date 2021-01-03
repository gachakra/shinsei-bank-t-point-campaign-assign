package dev.notonza.shinsei.application.notification;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
public class SlackReportingFailedException extends RuntimeException {

    public SlackReportingFailedException() {
    }

    public SlackReportingFailedException(String message) {
        super(message);
    }

    public SlackReportingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SlackReportingFailedException(Throwable cause) {
        super(cause);
    }

    public SlackReportingFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
