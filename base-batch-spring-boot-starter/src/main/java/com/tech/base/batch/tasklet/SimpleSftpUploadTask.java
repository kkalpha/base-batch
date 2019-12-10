package com.tech.base.batch.tasklet;

import com.tech.base.batch.property.BatchSFTPProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleSftpUploadTask extends SftpUploadTask {
    final static Logger logger = LoggerFactory.getLogger(SimpleSftpUploadTask.class);

    @Autowired
    BatchSFTPProperties batchSFTPProperties;

    @Override
    protected String getLoadLocalFilenameWithPath() {
        return batchSFTPProperties.getUploadLocalFilenameWithPath();
    }

}
