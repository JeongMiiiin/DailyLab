package com.amor4ti.dailylab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DailyLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyLabApplication.class, args);
	}

}
