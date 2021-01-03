package dev.notonza.shinsei.domain.models;

/**
 * @author gachakra
 * Created on 2020/12/07.
 */
public enum TaskResult {

    SUCCESS,
    ALREADY_DONE_THIS_MONTH,
    COMPLETE_PAGE_TEXT_NOT_SEEN,
    NETWORK_ERROR,
    CRITICAL_ERROR;
}
