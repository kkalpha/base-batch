package com.tech.base.batch.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = {"classpath:base-config.properties"})
@Component
@ConfigurationProperties(prefix = "base.batch.sftp")
public class BatchSFTPProperties {
    private String host;
    private int port=22;
	private String user;
	private String password;

	private String uploadDefaultPath="/";
	private String uploadLocalFilenameWithPath="/";

	private String downloadLocalFilenameWithPath="/";
	private String downloadRemoteFilenameWithPath="/";

	private String useLocalMachine;
	private Integer retryTimes = 3;
	private Integer retryInterval = 20000;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUploadDefaultPath() {
		return uploadDefaultPath;
	}

	public void setUploadDefaultPath(String uploadDefaultPath) {
		this.uploadDefaultPath = uploadDefaultPath;
	}

	public String getUploadLocalFilenameWithPath() {
		return uploadLocalFilenameWithPath;
	}

	public void setUploadLocalFilenameWithPath(String uploadLocalFilenameWithPath) {
		this.uploadLocalFilenameWithPath = uploadLocalFilenameWithPath;
	}

	public String getDownloadLocalFilenameWithPath() {
		return downloadLocalFilenameWithPath;
	}

	public void setDownloadLocalFilenameWithPath(String downloadLocalFilenameWithPath) {
		this.downloadLocalFilenameWithPath = downloadLocalFilenameWithPath;
	}

	public String getDownloadRemoteFilenameWithPath() {
		return downloadRemoteFilenameWithPath;
	}

	public void setDownloadRemoteFilenameWithPath(String downloadRemoteFilenameWithPath) {
		this.downloadRemoteFilenameWithPath = downloadRemoteFilenameWithPath;
	}

	public String getUseLocalMachine() {
		return useLocalMachine;
	}

	public void setUseLocalMachine(String useLocalMachine) {
		this.useLocalMachine = useLocalMachine;
	}

	public Integer getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}

	public Integer getRetryInterval() {
		return retryInterval;
	}

	public void setRetryInterval(Integer retryInterval) {
		this.retryInterval = retryInterval;
	}
}
