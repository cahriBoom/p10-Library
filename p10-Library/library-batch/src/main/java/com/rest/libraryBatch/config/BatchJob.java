package com.rest.libraryBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class BatchJob {

	 @Autowired
	 private JobLauncher jobLauncher;
	
	 @Autowired
	 private Job job;
	
	 
	 @Scheduled(cron = "0 0 8 * * *")
	 public void lendingRevival() throws Exception {
		 JobParameters params = new JobParametersBuilder()
				 .addString("sendReminderJob", String.valueOf(System.currentTimeMillis()))
				 .toJobParameters();
		 jobLauncher.run(job, params);
	 }
	
}
