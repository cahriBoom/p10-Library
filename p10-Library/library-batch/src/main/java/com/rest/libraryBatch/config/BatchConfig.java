package com.rest.libraryBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rest.libraryBatch.steps.RetardTasklet;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	
	public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    public final RetardTasklet retardTasklet;
    
    
    @Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, RetardTasklet retardTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.retardTasklet = retardTasklet;
    }

    @Bean
    public Job sendReminderJob(){
        return jobBuilderFactory.get("sendReminderJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne()).build();
    }

    @Bean
    public Step stepOne(){
    	return stepBuilderFactory.get("stepOne").tasklet(retardTasklet).build();
    }
    
    
    
    
    
}

