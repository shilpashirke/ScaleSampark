package com.scaleSampark.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com.*"})
@EnableJpaRepositories(basePackages="com.scaleSampark.repository")
@EntityScan(basePackages="com.scaleSampark.entity")
public class BootstrapApplication {
	 public static void main(String[] args) throws Exception {
	        SpringApplication.run(BootstrapApplication.class, args);
	    }
}
