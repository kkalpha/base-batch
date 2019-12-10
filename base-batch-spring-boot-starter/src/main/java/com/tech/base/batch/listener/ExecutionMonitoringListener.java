 package com.tech.base.batch.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* to extend:
 */
 @Component
 public class ExecutionMonitoringListener {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionMonitoringListener.class);
    @Autowired(required = false)
    private AfterJobEventHandler[] afterJobEventHandlers;

    @BeforeJob
    public void executeBeforeJob(JobExecution jobExecution) {
        // Do nothing
        logger.info("started job: {}",jobExecution.getJobConfigurationName());
    }

    @AfterJob
    public void executeAfterJob(JobExecution jobExecution) {
        if (null != afterJobEventHandlers) {
            for (AfterJobEventHandler handler : afterJobEventHandlers) {
                handler.handle(jobExecution);
            }
        }
    }
}
