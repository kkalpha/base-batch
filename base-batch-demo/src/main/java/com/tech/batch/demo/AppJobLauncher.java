package com.tech.batch.demo;

import com.tech.base.batch.constant.Constant;
import com.tech.base.batch.executor.JobExecutor;
import com.tech.batch.demo.schedule.DemoJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class AppJobLauncher {
    private static final Logger logger = LoggerFactory.getLogger(DemoJob.class);

    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        set.add("classpath:test.xml");
        set.add("classpath:fileToDatabase.xml");
        set.add("classpath:fileToFromSftp.xml");
        SpringApplication app = new SpringApplication(AppJobLauncher.class);
        app.setSources(set);
        ConfigurableApplicationContext ctx = app.run(args);

        startJob(ctx,"test");
        //startJob(ctx,"fileToDatabase");
        //startJob(ctx,"fileToFromSftp");

        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void startJob(ConfigurableApplicationContext ctx,String jobName) {
        try {

            logger.info("************* start job date {}", new Date());

            JobParametersBuilder parBuilder = new JobParametersBuilder();
            parBuilder.addLong(Constant.PARAM_START_TIME, System.currentTimeMillis());
            parBuilder.addString(Constant.JOB_NAME, jobName);

            JobParameters jobParameters = parBuilder.toJobParameters();

            JobExecutor jobExecutor = (JobExecutor)ctx.getBean("jobExecutor");

            jobExecutor.executeJob(ctx,jobName,jobParameters);

            logger.info("************** Done");

        } catch (Exception e) {
            //TODO
            logger.error("", e);
        }

    }
}
