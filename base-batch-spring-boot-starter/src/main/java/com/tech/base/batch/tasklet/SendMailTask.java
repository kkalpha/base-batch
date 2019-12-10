package com.tech.base.batch.tasklet;

import com.tech.base.batch.email.EmailObject;
import com.tech.base.batch.email.EmailService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.base.batch.listener.StagingListener;
@Component
public class SendMailTask implements Tasklet {

	final static Logger logger = LoggerFactory.getLogger(SimpleSftpUploadTask.class);
	@Autowired
	private EmailService emailService;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
			throws Exception {
        String content = (String)chunkContext.getStepContext().getJobExecutionContext().get("summary");

		EmailObject obj = emailService.buildEmailObject().setSubject("Dear \r\nHere is the batch job status: \r\n").setContent(content);

		emailService.sendTextMailIgnoreException(obj);

		return null;
	}

}
