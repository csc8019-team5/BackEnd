package uk.ac.ncl.team5project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Main class for starting the Team 5 Library Management Application.
 *
 * This class bootstraps the Spring Boot application and enables scheduled tasks.
 *
 * Author: Yixin Zhang
 * Created on: 01/04/2025
 */

@SpringBootApplication
@EnableScheduling  // Enable scheduled tasks
public class Team5ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team5ProjectApplication.class, args);

    }

}
