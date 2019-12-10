package com.tech.base.batch.autoconfigure;

import com.jcraft.jsch.ChannelSftp;
import com.tech.base.batch.property.BatchEmailProperties;
import com.tech.base.batch.property.BatchSFTPProperties;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
import org.springframework.stereotype.Component;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.tech")
@PropertySource(value = {"classpath:base-config.properties"})
public class BaseBatchAutoConfiguration {

    @Autowired
    BatchEmailProperties batchEmailProperties;
    @Autowired
    BatchSFTPProperties batchSFTPProperties;

    @Bean
    @ConditionalOnProperty(name = "base.batch.use-map-repository", havingValue = "true", matchIfMissing = true)
    public BatchConfigurer batchConfigurer() {
        return new DefaultBatchConfigurer() {
            @Override
            protected JobRepository createJobRepository() throws Exception {
                MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
                return factory.getObject();
            }
        };
    }

    @Bean
    public JavaMailSender batchMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(batchEmailProperties.getHost());
        mailSender.setPort(batchEmailProperties.getPort());
        mailSender.setUsername(batchEmailProperties.getUser());
        mailSender.setPassword(batchEmailProperties.getPassword());
        return mailSender;
    }

    @Bean
    public SessionFactory<ChannelSftp.LsEntry> sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);

        factory.setHost(batchSFTPProperties.getHost());
        factory.setPort(batchSFTPProperties.getPort());
        factory.setUser(batchSFTPProperties.getUser());
        factory.setPassword(batchSFTPProperties.getPassword());
        factory.setAllowUnknownKeys(true);

        return factory;
    }

    @Bean
    public SftpRemoteFileTemplate sftpRemoteFileTemplate() {
        SftpRemoteFileTemplate sftpRemoteFileTemplate = new SftpRemoteFileTemplate(sftpSessionFactory());
        sftpRemoteFileTemplate.setRemoteDirectoryExpression(new LiteralExpression(batchSFTPProperties.getUploadDefaultPath()));
        return sftpRemoteFileTemplate;
    }

}
