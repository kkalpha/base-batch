package com.tech.base.batch.listener;

import com.tech.base.batch.email.EmailObject;
import com.tech.base.batch.email.EmailService;
import com.tech.base.batch.tasklet.SimpleSftpUploadTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = {"classpath:base-config.properties"})
@Component
@ConditionalOnProperty(name = "base.batch.notify.status.byemail", havingValue = "true", matchIfMissing = true)
public class DefaultAfterJobEmailNotifier implements AfterJobEventHandler {
    final static Logger logger = LoggerFactory.getLogger(SimpleSftpUploadTask.class);
    @Autowired
    private EmailService emailService;

    private void sendEmail(JobExecution jobExecution) {
        String subject = null;
        String content = (String) jobExecution.getExecutionContext().get("summary");
        if (jobExecution.getStatus() == BatchStatus.FAILED) {

            subject = "Failed: " + jobExecution.getJobParameters().getString("jobname");
        } else {
            subject = "Completed: " + jobExecution.getJobParameters().getString("jobname");
        }

        EmailObject obj = emailService.buildEmailObject().setSubject(subject).setContent(content);

        emailService.sendTextMailIgnoreException(obj);

    }

    @Override
    public void handle(JobExecution jobExecution) {
        sendEmail(jobExecution);

    }

}
