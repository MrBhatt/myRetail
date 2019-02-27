package com.target.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Configure RestTemplate as a Spring Managed Bean to be able to inject it anywhere in the application
     **/
    @Bean(name = "restTemplate")
    public RestTemplate getRestClient() {
        return new RestTemplate();
    }
}
