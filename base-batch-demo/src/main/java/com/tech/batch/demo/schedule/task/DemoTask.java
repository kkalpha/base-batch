package com.tech.batch.demo.schedule.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class DemoTask implements Tasklet {
    final static Logger logger = LoggerFactory.getLogger(DemoTask.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.debug("demo task started " + this);

        chunkContext.getStepContext().getStepExecution().getExecutionContext().putLong("step-long",50000L);
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putLong("job-long",20000L);

        logger.debug("getJobExecutionContext#1 " + chunkContext.getStepContext().getJobExecutionContext().get("job-long"));
        logger.debug("getJobExecution().getExecutionContext()# " + chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("job-long"));

        logger.debug("demo task  done");
        return RepeatStatus.FINISHED;
    }
}