package com.sky.config;

import com.sky.properties.TaskExecutorPropertie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableScheduling
public class TaskExecutor {
    @Autowired
    private TaskExecutorPropertie propertie;

    @Bean(name = "taskThreadPool")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(propertie.getCorePoolSize());
        taskExecutor.setMaxPoolSize(propertie.getMaxPoolSize());
        taskExecutor.setQueueCapacity(propertie.getQueueCapacity());
        taskExecutor.setKeepAliveSeconds(propertie.getKeepAliveSeconds());
        taskExecutor.setThreadNamePrefix("TaskExecutor-");
        return taskExecutor;
    }
}
