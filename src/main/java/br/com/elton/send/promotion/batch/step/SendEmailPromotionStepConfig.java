package br.com.elton.send.promotion.batch.step;

import br.com.elton.send.promotion.entity.InterestProductClientEntity;
import br.com.elton.send.promotion.utils.ConstantesUtils;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SendEmailPromotionStepConfig {

    @Bean
    public Step sendEmailPromotionStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                                              ItemReader<InterestProductClientEntity> interestProductReader,
                                              ItemProcessor<InterestProductClientEntity, InterestProductClientEntity> processorEmailClientProcessor,
                                              ItemWriter<InterestProductClientEntity> sendEmailClientWriter) {

        return new StepBuilder("sendEmailPromotionStep", jobRepository)
                .<InterestProductClientEntity, InterestProductClientEntity>chunk(ConstantesUtils.SIZE_CHUNK, transactionManager)
                .reader(interestProductReader)
                .processor(processorEmailClientProcessor)
                .writer(sendEmailClientWriter)
                .build();
    }

}
