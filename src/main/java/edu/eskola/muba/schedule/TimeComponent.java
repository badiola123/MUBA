package edu.eskola.muba.schedule;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;


public class TimeComponent {
	
    
    private TaskScheduler taskScheduler;
    
    public TimeComponent() {
    	Config cg = new Config();
    	taskScheduler = cg.taskScheduler();
    }

    public void scheduling(final Runnable task, Date date) {
        // Schedule a task to run once at the given date (here in 1minute)
    	taskScheduler.schedule(task, date);

    }
}
