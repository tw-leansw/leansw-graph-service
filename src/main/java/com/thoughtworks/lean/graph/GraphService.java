package com.thoughtworks.lean.graph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yongliuli on 8/26/16.
 */
@EnableCaching
@SpringBootApplication
@Configuration
@EnableFeignClients
@ComponentScan
public class GraphService {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(GraphService.class, args);
    }
}
