package com.shishir.concurrency;

import com.shishir.concurrency.entities.Suite;
import com.shishir.concurrency.repository.SuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MainApplication {
	@Autowired
	private SuiteRepository suiteRepository;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@PostConstruct
	public void initSuite(){
		Suite suite = new Suite();
		suite.setStatus("AVAILABLE");
		suite.setName("Presidential Suite");

		suiteRepository.save(suite);
	}
}
