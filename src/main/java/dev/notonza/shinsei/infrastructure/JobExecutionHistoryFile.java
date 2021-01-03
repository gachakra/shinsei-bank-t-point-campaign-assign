package dev.notonza.shinsei.infrastructure;

import dev.notonza.shinsei.domain.models.JobExecutionHistoryRepository;
import dev.notonza.shinsei.domain.models.TaskResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @author gachakra
 * Created on 2020/12/07.
 */
@Slf4j
final public class JobExecutionHistoryFile implements JobExecutionHistoryRepository {

    private final static Path FILE_PATH = Paths.get("datasources/job-execution-history.tsv");

    public LocalDateTime findLatestDateTimeByTaskResult(TaskResult result) {

        createFileIfNotExists(FILE_PATH);

        try (ReversedLinesFileReader reader = new ReversedLinesFileReader(
            FILE_PATH.toFile(),
            StandardCharsets.UTF_8)) {

            String current;
            while ((current = reader.readLine()) != null) {
                if (current.contains(result.name())) {
                    String latestSucceededDateTimeString = Arrays.asList(current.split("\t", -1)).get(0);

                    return LocalDateTime.parse(
                        latestSucceededDateTimeString,
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
            }

            return LocalDateTime.MIN;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void persist(TaskResult result) {

        createFileIfNotExists(FILE_PATH);

        String line = String.format("%s\t%s%s", LocalDateTime.now(), result.name(), System.lineSeparator());

        try {
            Files.write(
                FILE_PATH,
                line.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createFileIfNotExists(Path path) {
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
                log.info("Created " + path + " because not exists");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
