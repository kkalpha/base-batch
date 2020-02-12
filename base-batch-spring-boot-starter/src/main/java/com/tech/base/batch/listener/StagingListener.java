package com.tech.base.batch.listener;

import com.tech.base.batch.constant.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/*
 * collect all steps execution summary and put in the execution context
 */

@Component
public class StagingListener implements StepExecutionListener {
	final static Log logger = LogFactory.getLog(StagingListener.class);
	private String separater = "\r\n";
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		StringBuilder  stepSummary = null;

		String summary = (String)stepExecution.getJobExecution().getExecutionContext().get(Constant.JOB_STATUS_SUMMARY);
		if(summary == null){
			stepSummary = new StringBuilder();
		}else{
			stepSummary = new StringBuilder(summary);
			stepSummary.append(this.separater);
		}

		// put the current step summary
		stepSummary.append("Step Result ");
		stepSummary.append(this.separater);
		stepSummary.append("Step Name: ");
		stepSummary.append(stepExecution.getStepName());
		stepSummary.append(this.separater);
		stepSummary.append("Step Summary: ");
		stepSummary.append(stepExecution.getSummary());
		stepSummary.append(this.separater);

		stepExecution.getJobExecution().getExecutionContext().put(Constant.JOB_STATUS_SUMMARY,stepSummary.toString());
		return null;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		// TODO Auto-generated method stub

	}

}
