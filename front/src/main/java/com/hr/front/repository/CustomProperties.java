package com.hr.front.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.hr.front")
public class CustomProperties {
	
	private String apiUrl;

}
