package com.synthilearn.workareaservice;

import com.synthilearn.loggingstarter.EnableLogging;
import com.synthilearn.securestarter.EnableTokenResolver;
import com.synthilearn.workareaservice.app.config.WebClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableTokenResolver
@EnableLogging
@EnableConfigurationProperties(WebClientProperties.class)
public class WorkareaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkareaServiceApplication.class, args);
    }

}
