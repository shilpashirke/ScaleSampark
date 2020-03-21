package com.scaleSampark.bootConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.scaleSampark"})
public class BootstrapApplication {
	 public static void main(String[] args) throws Exception {
	        SpringApplication.run(BootstrapApplication.class, args);
	    }
}
