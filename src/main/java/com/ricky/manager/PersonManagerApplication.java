package com.ricky.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author rickycharles
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.ricky.manager.mapper")
public class PersonManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonManagerApplication.class, args);
    }

}
