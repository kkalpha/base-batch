package com.tech.base.batch.email;


import com.tech.base.batch.property.BatchEmailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
public class EmailServiceImpl implements EmailService{
    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private BatchEmailProperties emailProperties;

    @Autowired
    private JavaMailSender batchMailSender;

    @Autowired
    protected EmailHelper emailHelper;


    @Override
    public void sendHTMLMailIgnoreException(EmailObject emailObject) {
        try {
            sendHTMLMail(emailObject);
        } catch (MessagingException e) {
            logger.error("send eamil error {}",e);
        } catch (IOException e) {
            logger.error("send eamil error {}",e);
        }
    }

    public void sendHTMLMail(EmailObject emailObject) throws MessagingException, IOException{
        sendMail(emailObject, true);
    }

    @Override
    public void sendTextMailIgnoreException(EmailObject emailObject) {
        try {
            sendTextMail(emailObject);
        } catch (MessagingException e) {
            logger.error("send eamil error {}",e);
        } catch (IOException e) {
            logger.error("send eamil error {}",e);
        }
    }

    public void sendTextMail(EmailObject emailObject) throws MessagingException, IOException {
        sendMail(emailObject, false);
    }

    private void sendMail(EmailObject emailObject, boolean isHTML) throws MessagingException, IOException {
        MimeMessage mailMessage = batchMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

        messageHelper.setFrom(emailObject.getFrom());
        messageHelper.setSubject(emailObject.getSubject());

        emailHelper.setReceipient(messageHelper, emailObject);

        emailHelper.setContent(isHTML, messageHelper, emailObject);

        emailHelper.sendEmail(batchMailSender, mailMessage, emailObject);

    }

    public EmailObject buildEmailObject(){
        EmailObject obj = new EmailObject();
        obj.setFrom(emailProperties.getDefaultFrom());
        obj.setTo(split(emailProperties.getDefaultTo()));
        obj.setCc(split(emailProperties.getDefaultCc()));
        obj.setBcc(split(emailProperties.getDefaultBcc()));
        return  obj;
    }

    private String[] split(String content){
        if(StringUtils.isEmpty(content)){
            return null;
        }else{
            return content.split(",");
        }
    }
}
