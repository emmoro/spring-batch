package br.com.elton.send.promotion.batch.writer;

import br.com.elton.send.promotion.entity.InterestProductClientEntity;
import br.com.elton.send.promotion.utils.EmailModal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.text.NumberFormat;

@Configuration
public class SendEmailClientWriterConfig {

    private static final Logger log = LoggerFactory.getLogger(SendEmailClientWriterConfig.class);

    @Autowired
    private EmailModal emailModal;

    @Bean
    public ItemWriter<InterestProductClientEntity> sendEmailClientWriter() {
        return items -> items.forEach(item -> {
            try {
                this.sendEmail(item);
            } catch (Exception e) {
                log.info(" Error: " + e.getMessage());
            }
        });
    }

    public void sendEmail(InterestProductClientEntity interestProductClient) throws Exception {
        String subject = "Test Email!";
        emailModal.sendEmail(subject, createTextPromotion(interestProductClient),
                new String[]{interestProductClient.getClient().getEmail()}, true);
    }

    private String createTextPromotion(InterestProductClientEntity interestProductClient) {
        StringBuilder writer = new StringBuilder();
        writer.append(String.format("Hello, %s!\n\n", interestProductClient.getClient().getName()));
        writer.append("This promotion can be your interest:\n\n");
        writer.append(String.format("%s - %s\n\n", interestProductClient.getProduct().getName(), interestProductClient.getProduct().getDescription()));
        writer.append(String.format("For only: %s!",
                NumberFormat.getCurrencyInstance().format(interestProductClient.getProduct().getPrice())));
        return writer.toString();
    }

}
