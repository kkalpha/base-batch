package com.tech.base.batch.email;

import com.tech.base.batch.property.BatchEmailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Arrays;

@Component
public class EmailHelper {
    private static Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private BatchEmailProperties emailProperties;

    public void setReceipient(MimeMessageHelper messageHelper, EmailObject emailObject) throws MessagingException {

        if (StringUtils.isEmpty(emailProperties.getDefaultRecipient())) {
            messageHelper.setTo(emailObject.getTo());
            logger.debug("send to {}", Arrays.toString(emailObject.getTo()));

            if (null != emailObject.getCc()) {
                logger.debug("cc to {}", Arrays.toString(emailObject.getCc()));
                messageHelper.setCc(emailObject.getCc());
            }

            if (null != emailObject.getBcc()) {
                logger.debug("bcc to {}", Arrays.toString(emailObject.getBcc()));
                messageHelper.setBcc(emailObject.getBcc());
            }
        } else {
            // in dev/testing environment, send email to a default recipient
            messageHelper.setTo(emailProperties.getDefaultRecipient().split(","));
            logger.debug("send to default {}", emailProperties.getDefaultRecipient());
        }

    }

    public void setContent(boolean isHTML, MimeMessageHelper messageHelper, EmailObject emailObject)
            throws MessagingException, IOException {

        if (isHTML) {
            messageHelper.setText(emailObject.getContent(), true); // is HTML format
        } else {
            messageHelper.setText(emailObject.getContent()); // text format
        }

        // EmailAttachment attachment = emailObject.getAttachment();
        // if (null != attachment) {
        // messageHelper.addAttachment(attachment.getAttachmentname(),
        // attachment.getInputStreamSource());
        // }

        // messageHelper.addAttachment("attachement name", new InputStreamSource() {
        // public InputStream getInputStream() throws IOException {
        // //return inStream;
        // return new ByteArrayInputStream("attachement contents".getBytes());
        // }
        // });

    }

    public void sendEmail(JavaMailSender mailSender, MimeMessage mailMessage, EmailObject emailObject)
            throws MailException {
        int i = 0;
        if (! "N".equals(emailProperties.getSendFlag())) {
            while (i <= emailProperties.getRetryTimes()) {
                try {
                    mailSender.send(mailMessage);
                    break;
                } catch (MailException e) {

                    if (i >= emailProperties.getRetryTimes()) {
                        // still failed after exceeding the retry times
                        throw e;
                    }

                    i++;
                    logger.info("send email error for {}, retry: {}, error is {}", Arrays.toString(emailObject.getTo()), i,e);
                    try {
                        int pow = (int) Math.pow(i, 2); // 1,4,9
                        Thread.sleep(pow * emailProperties.getRetryInterval());
                    } catch (InterruptedException e1) {
                        // do nothing
                    }
                }
            }

        } else {
            // just log the content
            logger.debug(emailObject.getContent());
        }

    }
}
