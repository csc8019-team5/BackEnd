package uk.ac.ncl.team5project.controller;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.ac.ncl.team5project.entity.Admin;
import uk.ac.ncl.team5project.entity.Book;
import uk.ac.ncl.team5project.form.BookInsertForm;
import uk.ac.ncl.team5project.form.BookModifyForm;
import uk.ac.ncl.team5project.param.BookInsertParam;
import uk.ac.ncl.team5project.service.AdminService;
import uk.ac.ncl.team5project.service.BookService;
import uk.ac.ncl.team5project.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @file AdminController.java
 * @date 2025-04-09 09:58
 * @function_description: 
 * @discussion: 
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: 
 *     @review_date: 
 *     @modification_date: 
 *     @description: 
 */

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private BookService bookService;

//METHOD 1: Qurey and display all books
    @GetMapping("/loadBooks")
    public Result loadBooks(){
        List<Book> books = bookService.loadBooks();
        return Result.success("200", books);
    }

//METHOD 2: Add a new book
    @PostMapping("/insert")
    public Result insert(@RequestBody BookInsertForm form) throws Exception{
        BookInsertParam param = new BookInsertParam();
        BeanUtils.copyProperties(form, param);
        bookService.insert(param);
        return Result.success("200",null);
    }

//METHOD 3: Modify a book
    @PatchMapping("/modify")
    public Result modify(@RequestBody BookModifyForm form){
        BookInsertParam param = new BookInsertParam();
        BeanUtils.copyProperties(form, param);
        bookService.modify(param);
        return Result.success("200",null);
    }

    /**
     * METHOD 4: delete a book 
     * @Note
     * This function will not actually 'DELETE' data from the database, 
     * but rather modify the "available" field in the "book" table to mark a book as "deleted", 
     * and then filter out the marked books when querying all books again. 
     * This method is used to preserve historical records.
     * @param bookId 
     * @return State code
     */
    @PatchMapping("/delete")
    public Result delete(@RequestBody String id) throws Exception{
        Integer bookId = new ObjectMapper().readTree(id).get("bookId").asInt();
        bookService.delete(bookId);
        return Result.success("200",null);
    }


//METHOD ?:  Qurey and display all administors from database
@GetMapping("/display")
public Result display(){
    List<Admin> admins = adminService.display();
    return Result.success("200", admins);
}



}
