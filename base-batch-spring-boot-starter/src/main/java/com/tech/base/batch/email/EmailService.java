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
public interface EmailService {

    public void sendHTMLMailIgnoreException(EmailObject emailObject);
    public void sendHTMLMail(EmailObject emailObject) throws MessagingException, IOException;
    public void sendTextMailIgnoreException(EmailObject emailObject);
    public void sendTextMail(EmailObject emailObject) throws MessagingException, IOException ;
    public EmailObject buildEmailObject();

}
