package com.tech.base.batch.tasklet;

import com.tech.base.batch.property.BatchSFTPProperties;
import com.tech.base.batch.sftp.SftpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public abstract class SftpDownloadTask implements Tasklet {

    @Autowired
    private SftpService sftpService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
            throws Exception {
        sftpService.downLoadFile(getRemoteFilenameWithPath(),getLoadLocalFilenameWithPath());

        return RepeatStatus.FINISHED;
    }

    protected abstract String getRemoteFilenameWithPath();
    protected abstract String getLoadLocalFilenameWithPath();
}
