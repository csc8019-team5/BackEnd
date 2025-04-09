package uk.ac.ncl.team5project.com.admin.controller;
/**
 * @file AdminController.java
 * @date 2025-04-09 02:58
 * @function_description: 
 * @interface_description: 
 *     @calling_sequence: 
 *     @arguments_description: 
 *     @list_of_subordinate_classes: 
 * @discussion: 
 * @development_history: 
 *     @designer USER 
 *     @reviewer: 
 *     @review_date: 
 *     @modification_date: 
 *     @description: 
 */

import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AdminController {

//METHOD 1:  Qurey and display all administors from database
    public List<Admin> display(){

        return null;
    }

//METHOD 2: Add a new Administor
    public void insert(){

    }

}
