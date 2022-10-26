package com.codex.profiler.profilerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProfilerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfilerServiceApplication.class, args);
    }

}
