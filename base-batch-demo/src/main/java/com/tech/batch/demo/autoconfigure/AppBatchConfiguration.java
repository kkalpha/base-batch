package com.tech.batch.demo.autoconfigure;

import com.jcraft.jsch.ChannelSftp;
import com.tech.base.batch.property.BatchEmailProperties;
import com.tech.base.batch.property.BatchSFTPProperties;
import com.tech.base.batch.tasklet.SftpDownloadTask;
import com.tech.base.batch.tasklet.SftpUploadTask;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource(value = {"classpath:base-config.properties"})
public class AppBatchConfiguration {

    @Autowired
    BatchEmailProperties batchEmailProperties;
    @Autowired
    BatchSFTPProperties batchSFTPProperties;

    @Bean
    public Tasklet extendedSftpDownloadTask() {
        Tasklet tasklet = new SftpDownloadTask(){

            @Override
            protected String getRemoteFilenameWithPath() {
                //TODO, get from configuration
                return "/temp/test.txt";
            }

            @Override
            protected String getLoadLocalFilenameWithPath() {
                return "C:\\Data\\app\\batch\\test-local.txt";
            }
        };

        return tasklet;
    }

    @Bean
    public Tasklet extendedSftpUploadTask() {
        Tasklet tasklet = new SftpUploadTask(){

            @Override
            protected String getLoadLocalFilenameWithPath() {
                return "C:\\Data\\app\\batch\\test-upload.txt";
            }
        };

        return tasklet;
    }

}
