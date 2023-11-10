package br.com.elton.send.promotion.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendEmailPromotionJobConfig {

    @Bean
    public Job sendEmailPromotionJob(JobRepository jobRepository, Step sendEmailPromotionStep) {
        return new JobBuilder("sendEmailPromotionJob", jobRepository)
                .start(sendEmailPromotionStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
