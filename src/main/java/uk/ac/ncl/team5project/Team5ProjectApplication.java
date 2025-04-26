package uk.ac.ncl.team5project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @file Team5ProjectApplication.java
 * @date 2025-04-01
 * @function_description: Main entry point of the Team 5 Spring Boot application.
 * @interface_description: Bootstraps the application, loads Spring context, and scans mapper interfaces.
 * @calling_sequence: Run main() → Load Spring context → Start web server → Initialize all beans
 * @arguments_description: String[] args - standard command-line arguments
 * @list_of_subordinate_classes: All @Component, @Service, @Controller, and @Mapper-annotated classes
 * @discussion: This class must be the top-level entry point and reside in the base package.
 * @development_history: Created on 2025-04-01 as the main bootstrapping class.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Starts the Spring Boot application and enables automatic component scanning.
 */

@SpringBootApplication
@MapperScan("uk.ac.ncl.team5project.mapper")
public class Team5ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team5ProjectApplication.class, args);
    }

}
