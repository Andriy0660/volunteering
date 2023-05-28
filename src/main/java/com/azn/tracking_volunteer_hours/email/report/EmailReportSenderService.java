package com.azn.tracking_volunteer_hours.email.report;


import com.azn.tracking_volunteer_hours.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmailReportSenderService implements EmailSender {

    private final UserService userService;
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailReportSenderService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(content, true);
            helper.setTo(to);
            helper.setSubject("Report for last 7 days activity");
            helper.setFrom("coco.team.corporation@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }
}