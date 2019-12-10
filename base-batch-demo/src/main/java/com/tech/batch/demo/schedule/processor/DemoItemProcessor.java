package com.tech.batch.demo.schedule.processor;

import com.tech.batch.demo.schedule.reader.DemoItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.batch.core.StepExecution;
@Component
public class DemoItemProcessor implements ItemProcessor<String, String> {
    private static Logger logger = LoggerFactory.getLogger(DemoItemReader.class);
    private JobExecution jobExecution;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        jobExecution = stepExecution.getJobExecution();
    }

    @Override
    public String process(String item) throws Exception {

        logger.debug("job name {}",jobExecution.getJobParameters().getString("jobname"));
        logger.debug("jobExecution.getExecutionContext() {}",jobExecution.getExecutionContext().get("long"));
        return item + " -- transform";
    }
}
