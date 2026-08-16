package org.xdclassredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class XdclassRedisApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(XdclassRedisApplication.class, args);
    }
}