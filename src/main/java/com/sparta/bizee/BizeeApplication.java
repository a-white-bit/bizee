package com.sparta.bizee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BizeeApplication {

	public static void main(String[] args) {
		String getVersion = org.springframework.core.SpringVersion.getVersion();
		System.out.println("Spring Ver. " + getVersion);
		SpringApplication.run(BizeeApplication.class, args);
	}

}
