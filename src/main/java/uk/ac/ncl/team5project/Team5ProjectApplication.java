package uk.ac.ncl.team5project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //  启用定时任务
public class Team5ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team5ProjectApplication.class, args);

    }

}
