package com.iiht.yaksha.fsdpa.notesapp.fsdpanotesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.iiht.yaksha.fsdpa.notesapp")
@EnableJpaRepositories(basePackages = "com.iiht.yaksha.fsdpa.notesapp.repo")
@EntityScan("com.iiht.yaksha.fsdpa.notesapp.model")
public class FsdpaNotesappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsdpaNotesappApplication.class, args);
	}
}
