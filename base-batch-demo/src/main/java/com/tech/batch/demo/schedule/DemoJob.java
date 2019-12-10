package com.tech.batch.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class DemoJob {
    private static final Logger logger = LoggerFactory.getLogger(DemoJob.class);

    @Autowired
    Job test;

    @Autowired
    JobLauncher jobLauncher;

    //@Scheduled(cron = "0 0/2 * * * ?")
    public void work() {
        try {

            logger.info("************* start job date {}", new Date());

            JobParametersBuilder parbuilder = new JobParametersBuilder();
            parbuilder.addLong("time", System.currentTimeMillis());
            parbuilder.addString("jobname", "scheduledTestJob");

            JobParameters jobParameters = parbuilder.toJobParameters();

            JobExecution execution = jobLauncher.run(test, jobParameters);
            execution.getExecutionContext().putLong("long",1000L);

            logger.info("Exit Status : {}", execution.getStatus());

            logger.info("************** Done");

        } catch (Exception e) {

            logger.error("", e);
        }

    }

}
