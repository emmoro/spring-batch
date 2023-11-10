package br.com.elton.send.promotion.batch.processor;

import br.com.elton.send.promotion.entity.InterestProductClientEntity;
import br.com.elton.send.promotion.utils.EmailValidatorUtil;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorEmailClientProcessorConfig {

    @Bean
    public ItemProcessor<InterestProductClientEntity, InterestProductClientEntity> processorEmailClientProcessor() {
        ValidatingItemProcessor<InterestProductClientEntity> processor = new ValidatingItemProcessor();
        processor.setValidator(validator());
        processor.setFilter(true);
        return processor;
    }

    private Validator<InterestProductClientEntity> validator() {
        return new Validator<InterestProductClientEntity>() {

            @Override
            public void validate(InterestProductClientEntity interestProductClient) throws ValidationException {
                if (!EmailValidatorUtil.isValidEmailAddressRegex(interestProductClient.getClient().getEmail())) {
                    throw new ValidationException(String.format("Email format incorrect %s!", interestProductClient.getClient().getEmail()));
                }
            }
        };
    }

}
