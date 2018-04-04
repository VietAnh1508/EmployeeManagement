package com.tma.employee.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.tma")
@PropertySource({"classpath:application.properties"})
public class AppConfig {

}
