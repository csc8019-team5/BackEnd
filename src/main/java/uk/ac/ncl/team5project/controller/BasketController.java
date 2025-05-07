package uk.ac.ncl.team5project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.ac.ncl.team5project.param.BasketInfoParam;
import uk.ac.ncl.team5project.param.BasketPaidParam;
import uk.ac.ncl.team5project.param.BookInsertParam;
import uk.ac.ncl.team5project.entity.Basket;
import uk.ac.ncl.team5project.form.BasketPaidForm;
import uk.ac.ncl.team5project.service.BasketService;
import uk.ac.ncl.team5project.util.Result;

/**
 * @file BasketController.java
 * @date 2025-04-09 09:58
 * @function_description: Manipulate all books from cart (CRUD)
 * @discussion: Considering an extended method which can check the book if already in the cart [finished]
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: Qingyu Cao
 *     @review_date: 04/05 2025
 *     @modification_date: 04/05 2025
 *     @description: 
 *  - Review all method and test API by Postman to make sure is accessible. 
 */

 @RestController
 @RequestMapping("/api/v1/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    private Integer storedUserId;


/**
* @method_1 Load and display all books from cart
* @throws Exception
* @return List<BasketInfroParam>
* @apiNote Display all books
*/
    @GetMapping("/loadBasket")
    public Result loadBasket(@RequestParam Integer userId) throws Exception{
        storedUserId = userId;
        List<BasketInfoParam> basketInfoParams = basketService.loadBasket(userId);
        return Result.success("200",basketInfoParams);
    }

/**
* @method_2 Check the book if already in the cart
* @return Boolean valid
* @param bookId,userId
* @throws Exception
*/
    @GetMapping("/check")
    public Result check(@RequestParam Integer bookId, @RequestParam Integer userId){
        Boolean valid = true;
        if (basketService.check(bookId,userId).size()>0) {
            return Result.success("200", valid = false);
        }
        return Result.success("200", valid);
    }


/**
* @method_3 Insert the book into the cart
* @return message only
* @param bookId,userId
*/
    @PostMapping("/insert")
    public Result insertByBookId(@RequestParam Integer bookId, @RequestParam Integer userId){
        basketService.insert(bookId,userId);
        return Result.success("200", null);
    }


/**
* @method_4 Insert the book into the cart
* @return message only
* @param bookId,userId
* @apiNote This method will only change the state of the the book which means be deleted
*/
    @PatchMapping("/delete")
    public Result deleteByBookId(@RequestParam Integer userId,@RequestParam Integer bookId){
        basketService.delete(userId,bookId);
        return Result.success("200", null);
    }

    //METHOD 5: Pay and add to USER_BOOK

    @GetMapping("/pay")
    public Result payAndRecord() throws Exception{
        List<BasketInfoParam> basketInfoParams = basketService.loadBasket(storedUserId);
        BasketPaidForm bpf = new BasketPaidForm();
        List<Integer> bookIds = new ArrayList<>();
        for(BasketInfoParam basketInfo : basketInfoParams){
            bookIds.add(basketInfo.getBookId());
        }
        bpf.setBookIds(bookIds);
        bpf.setUserId(storedUserId);
        BasketPaidParam bpp = new BasketPaidParam();
        BeanUtils.copyProperties(bpf, bpp);
        basketService.pay(bpp);
        return Result.success("200",null);
    }




}
