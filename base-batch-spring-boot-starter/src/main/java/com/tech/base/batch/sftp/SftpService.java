package com.tech.base.batch.sftp;

public interface SftpService {
    public void downLoadFile(String remoteFileNameWithPath,String localFileNameWithPath);
    public void upload(String localFileNameWithPath);

}
