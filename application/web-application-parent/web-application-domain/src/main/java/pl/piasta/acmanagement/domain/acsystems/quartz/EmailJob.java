package pl.piasta.acmanagement.domain.acsystems.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailJob extends QuartzJobBean {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("Executing job id: {}", jobExecutionContext.getJobDetail().getKey());
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String recipient = jobDataMap.getString("email");
        String subject = jobDataMap.getString("subject");
        String body = jobDataMap.getString("body");
        sendMail(mailProperties.getUsername(), recipient, subject, body);
    }

    private void sendMail(String sender, String recipient, String subject, String body) {
        try {
            log.info("Sending email to {}", recipient);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            messageHelper.setFrom(sender);
            messageHelper.setTo(recipient);
            mailSender.send(message);
        } catch (MessagingException ex) {
            log.error("Failed to send email to {}", recipient);
        }
    }
}
