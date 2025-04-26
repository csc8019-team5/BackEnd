package uk.ac.ncl.team5project.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @file Team5ProjectApplication.java
 * @date 2025-04-11 02:50
 * @function_description: 
 * @interface_description: 
 *     @calling_sequence: 
 *     @arguments_description: 
 *     @list_of_subordinate_classes: 
 * @discussion: 
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: 
 *     @review_date: 
 *     @modification_date: 
 *     @description: 
 */


@SpringBootApplication
@MapperScan(basePackages = "uk.ac.ncl.team5project.com.admin.mapper;")
public class Team5ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team5ProjectApplication.class, args);
    }

}
