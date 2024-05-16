package com.sparta.bizee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BizeeApplication {

	public static void main(String[] args) {
		String getVersion = org.springframework.core.SpringVersion.getVersion();
		System.out.println("Spring Ver. " + getVersion);
		SpringApplication.run(BizeeApplication.class, args);
	}

}
