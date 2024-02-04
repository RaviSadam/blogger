package com.springboot.blog.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.blog.BlogApplication;
import com.springboot.blog.Repositories.BlogRepository;

import lombok.extern.log4j.Log4j2;

@Component
@EnableScheduling
@Log4j2
public class PeriodTasksService {

    @Autowired
    private BlogRepository blogRepository;
    
    //calling GC for every 2 mins
	@Scheduled(fixedRate = 300000,initialDelay = 600000)
    public void gerbageCollection(){
		System.gc();
        log.info("GC executed");
	}

    //updating the todaysDate at midnigth 12 and update the blogs
    @Scheduled(cron="0 0 0 * * *")
    public void updateDate(){
        BlogApplication.todaysDate=new Date(System.currentTimeMillis());
        blogRepository.updateStatus();        
    }
}
