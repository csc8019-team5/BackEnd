package uk.ac.ncl.team5project.com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.ac.ncl.team5project.com.admin.Param.BasketInfoParam;
import uk.ac.ncl.team5project.com.admin.entity.Basket;
import uk.ac.ncl.team5project.com.admin.service.BasketService;
import uk.ac.ncl.team5project.com.admin.util.Result;

/**
 * @file BasketController.java
 * @date 2025-04-19 01:43
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

 @RestController
 @RequestMapping("/api/v1/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    


//METHOD 1: Qurey books by userId with valid
    @GetMapping("/loadBasket")
    public Result loadBasket(@RequestBody Integer userId) throws Exception{
        List<BasketInfoParam> basketInfoParams = basketService.loadBasket(userId);
        return Result.success("200",basketInfoParams);
    }

//METHOD 2: Check the book if is already put into basket
    @GetMapping("/check")
    public Result check(@RequestBody Integer bookId, @RequestBody Integer userId){
        Boolean valid = true;
        if (basketService.check(bookId,userId).size()>0) {
            return Result.success("200", valid = false);
        }
        return Result.success("200", valid);
    }


//METHOD 3: Insert the book into the basket
    @PostMapping("/insert")
    public Result insertByBookId(@RequestBody Integer bookId, @RequestBody Integer userId){
        basketService.insert(bookId,userId);
        return Result.success("200", null);
    }


//METHOD 4: Delete the book from basket
    @PatchMapping("/delete")
    public Result deleteByBookId(@RequestBody Integer userId,@RequestBody Integer bookId){
        basketService.delete(userId,bookId);
        return Result.success("200", null);
    }




}
