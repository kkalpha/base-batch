package com.tech.base.batch.sftp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SftpServiceImpl implements SftpService{
    @Autowired
    private SftpRemoteFileTemplate sftpRemoteFileTemplate;

    public void downLoadFile(String remoteFileNameWithPath,String localFileNameWithPath){
        sftpRemoteFileTemplate.get(remoteFileNameWithPath,new LocalFileInputStreamCallback(localFileNameWithPath));
    }

    public void upload(String localFileNameWithPath)
    {
        File file = new File(localFileNameWithPath);
        Message<File> message = MessageBuilder.withPayload(file).build();
        //sftpRemoteFileTemplate.setRemoteDirectoryExpression(new LiteralExpression("/temp"));
        String send = sftpRemoteFileTemplate.send(message,FileExistsMode.REPLACE);
    }

}
