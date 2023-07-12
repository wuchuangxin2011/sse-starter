package com.wwl.sse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wwl
 */
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class},
        scanBasePackages = {"com.wwl.sse"})
public class SseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SseApplication.class, args);
    }
}
