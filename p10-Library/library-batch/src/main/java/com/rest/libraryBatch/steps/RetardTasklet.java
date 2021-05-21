package com.rest.libraryBatch.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.libraryBatch.service.BatchService;

@Component
public class RetardTasklet implements Tasklet{

	@Autowired
	private BatchService batchService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		batchService.sendMail();
		return RepeatStatus.FINISHED;
	}

}