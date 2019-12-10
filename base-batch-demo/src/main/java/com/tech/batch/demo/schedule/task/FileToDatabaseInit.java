package com.tech.batch.demo.schedule.task;

import com.tech.batch.demo.schedule.service.FileToDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileToDatabaseInit implements Tasklet {
    private static final Logger logger = LoggerFactory.getLogger(FileToDatabaseInit.class);

    @Autowired
    private FileToDatabaseService fileToDatabaseService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.debug("Start processing... clean data");
        fileToDatabaseService.init();

        return RepeatStatus.FINISHED;
    }
}
