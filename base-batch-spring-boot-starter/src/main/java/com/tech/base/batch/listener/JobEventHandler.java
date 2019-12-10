 package com.tech.base.batch.listener;

import org.springframework.batch.core.JobExecution;

 public interface JobEventHandler {
    void handle(JobExecution jobExecution);
}
