package com.tech.base.batch.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = {"classpath:base-config.properties"})
@Component
@ConfigurationProperties(prefix = "base.batch.email")
public class BatchEmailProperties {
    private String host;
    private int port=25;
	private String user;
	private String password;

	private String defaultFrom="";
	private String defaultTo="";
	private String defaultCc="";
	private String defaultBcc="";

	private String defaultRecipient;

	private String sendFlag;
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

	public String getDefaultFrom() {
		return defaultFrom;
	}

	public void setDefaultFrom(String defaultFrom) {
		this.defaultFrom = defaultFrom;
	}

	public String getDefaultTo() {
		return defaultTo;
	}

	public void setDefaultTo(String defaultTo) {
		this.defaultTo = defaultTo;
	}

	public String getDefaultCc() {
		return defaultCc;
	}

	public void setDefaultCc(String defaultCc) {
		this.defaultCc = defaultCc;
	}

	public String getDefaultBcc() {
		return defaultBcc;
	}

	public void setDefaultBcc(String defaultBcc) {
		this.defaultBcc = defaultBcc;
	}

	public String getDefaultRecipient() {
		return defaultRecipient;
	}

	public void setDefaultRecipient(String defaultRecipient) {
		this.defaultRecipient = defaultRecipient;
	}

	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
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
