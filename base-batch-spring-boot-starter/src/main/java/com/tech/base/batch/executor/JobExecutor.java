package com.tech.base.batch.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JobExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JobExecutor.class);
    @Autowired
    JobLauncher jobLauncher;

    public void executeJob(ApplicationContext ctx, String jobName, JobParameters jobParameters) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        logger.info("start job: " + jobName);
        Job job = (Job) ctx.getBean(jobName);
        JobExecution execution = jobLauncher.run(job, jobParameters);
        logger.info("Exit Status : " + execution.getStatus());

    }
}
