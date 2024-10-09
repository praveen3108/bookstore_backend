package com.lab.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.lab.bookstore.*")
@EntityScan("com.lab.bookstore.*")
@EnableJpaRepositories( basePackages = "com.lab.bookstore.*")
public class BookstoreApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

}
