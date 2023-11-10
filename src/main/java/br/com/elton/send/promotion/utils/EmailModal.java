package br.com.elton.send.promotion.utils;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Properties;

@Component
public class EmailModal {

    private static final Logger log = LoggerFactory.getLogger(EmailModal.class);

    static final String FROM = "contato@teste.com.br";
    static final String FROMNAME = "TESTE";
    static final String SMTP_USERNAME = "123456789";
    static final String SMTP_PASSWORD = "123456780000";
    static final String HOST = "email.teste.com";
    static final int PORT = 111;

    public void sendEmail(String subject, String message, String[] to, boolean hiddenCopy) throws Exception {

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", HOST);

        Session session = Session.getDefaultInstance(props);
        String htmlBody = "";
        htmlBody += message;
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));

        for (String emailTo : to) {
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
        }

        msg.setSubject(subject, "ISO-8859-1");
        msg.setHeader("Content-Type", "text/html; charset=\"iso-8859-1\"");
        msg.setContent(htmlBody, "text/html; charset=iso-8859-1");
        msg.setHeader("Content-Transfer-Encoding", "quoted-printable");

        Transport transport = session.getTransport();

        try {
            log.info("***********");
            log.info("Try to connect...");
            log.info("***********");
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            log.info("***********");
            log.info("Connected!");
            log.info("***********");
            transport.sendMessage(msg, msg.getAllRecipients());
            log.info("***********");
            log.info("Email Sent!");
            log.info("***********");
        } catch (Exception ex) {
            log.error("***********");
            log.error("The email was not sent. Error message : " + ex.getMessage());
            log.error("***********");
        } finally {
            transport.close();
        }
    }

}
