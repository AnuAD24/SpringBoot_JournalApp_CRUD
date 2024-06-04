package com.jsp1.journalap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.jsp1.journalap.repository.JournalEntryRepository;

@SpringBootApplication(scanBasePackages = {"com.jsp1.journalap"})
public class JournalapApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalapApplication.class, args);
	}

}
