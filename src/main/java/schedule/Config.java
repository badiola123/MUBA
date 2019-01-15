package schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
@EnableScheduling
public class Config {

 @Bean
 public TaskScheduler taskScheduler() {
	 			ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	            scheduler.setPoolSize(10);
	            scheduler.initialize();
	            return scheduler;
 }
}