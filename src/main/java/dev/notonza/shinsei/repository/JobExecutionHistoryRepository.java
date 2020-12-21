package dev.notonza.shinsei.repository;

import com.google.inject.ImplementedBy;
import dev.notonza.shinsei.models.TaskResult;

import java.time.LocalDateTime;

/**
 * @author gachakra
 * Created on 2020/12/07.
 */
@ImplementedBy(JobExecutionHistoryFile.class)
public interface JobExecutionHistoryRepository {

    LocalDateTime findLatestDateTimeByTaskResult(TaskResult result);

    void persist(TaskResult result);
}
