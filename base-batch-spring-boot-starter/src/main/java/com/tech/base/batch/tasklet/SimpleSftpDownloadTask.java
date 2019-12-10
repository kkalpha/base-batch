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

/*

 */
@Component
public class SimpleSftpDownloadTask extends SftpDownloadTask {
    final static Logger logger = LoggerFactory.getLogger(SimpleSftpDownloadTask.class);

    @Autowired
    BatchSFTPProperties batchSFTPProperties;

    @Override
    protected String getRemoteFilenameWithPath() {
        return batchSFTPProperties.getDownloadRemoteFilenameWithPath();
    }

    @Override
    protected String getLoadLocalFilenameWithPath() {
        return batchSFTPProperties.getDownloadLocalFilenameWithPath();
    }

}
