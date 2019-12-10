package com.tech.base.batch.sftp;

import org.springframework.integration.file.remote.InputStreamCallback;
import org.springframework.util.FileCopyUtils;

import java.io.*;

public class LocalFileInputStreamCallback implements InputStreamCallback {
    private String localFileNameWithPath;

    public LocalFileInputStreamCallback(String localFileNameWithPath) {
        this.localFileNameWithPath = localFileNameWithPath;
    }

    @Override
    public void doWithInputStream(InputStream inputStream) throws IOException {
        OutputStream outputStream = null;
        File file = new File(this.localFileNameWithPath);

        outputStream = new FileOutputStream(file);

        FileCopyUtils.copy(inputStream,outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
