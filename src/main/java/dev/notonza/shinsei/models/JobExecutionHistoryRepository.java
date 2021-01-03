package dev.notonza.shinsei.models;

import com.google.inject.ImplementedBy;
import dev.notonza.shinsei.infrastructure.JobExecutionHistoryFile;

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
