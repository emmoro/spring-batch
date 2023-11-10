package br.com.elton.send.promotion.job;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendPromotionScheduleJob {

    private static final Logger log = LoggerFactory.getLogger(SendPromotionScheduleJob.class);

    private final Job job;

    private final JobExplorer jobExplorer;

    private final JobLauncher jobLauncher;

    @Scheduled(cron = "0 */2 * * * *")//every 2 minutes
    public void runBatchJob() {
        try {
            final JobExecution execution = jobLauncher.run(job,
                    new JobParametersBuilder(this.jobExplorer)
                            .getNextJobParameters(this.job)
                            .toJobParameters());

        } catch (JobExecutionAlreadyRunningException | JobRestartException |
                JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            log.info(" Error: " + e.getMessage());
        }
    }

}
