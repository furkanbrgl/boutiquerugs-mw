package com.boutiquerugsmw.util;

import com.boutiquerugsmw.model.MailContent;
import com.boutiquerugsmw.model.ScheduledTestModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailUtil {

    private static final Logger logger = Logger.getLogger(MailUtil.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String from, String[] to, String subject, String text) {
        try {
            MimeMessage message = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            this.mailSender.send(message);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sendMail(String from, String[] to, String subject, MailContent mailContent, ScheduledTestModel scheduledTestModel, String[] attachmentPaths) {

        try {

            MimeMessage message = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(mailContent.getHTMLContent(scheduledTestModel), false);

            File file;
            for (String attachmentPath : attachmentPaths) {
                file = new File(attachmentPath);
                if (file.exists()) {
                    helper.addAttachment(file.getName(), file);
                }
            }

            this.mailSender.send(message);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
