package uk.ac.ncl.team5project.controller;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * @function_description: Administor can manipulate all books (CRUD)
 * @discussion: Considering an extended method can activate the deleted book
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: Qingyu Cao
 *     @review_date: 07/05 2025
 *     @modification_date: 07/05 2025
 *     @description: 
 *  - Added a method to query all current administrators, making it convenient for future expansion.
 *  - Review all method and test API by Postman to make sure is accessible. 
 */

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private BookService bookService;

/**
* @method_1 Qurey and display all books
* @return List<Book>
* @apiNote This api will display all books in database
*          It's worth noting that as for administrator, 
*         this method will also display [books which already deleted]
*/
    @GetMapping("/loadBooks")
    public Result loadBooks() {
        List<Book> books = bookService.loadBooks();
        return Result.success("200", books);
    }

/**
* @method_2 Insert a book into the database.
* @return message only
* @param BookInsertForm
* @throws Exception
* @apiNote This method will receive the form from Front-end,
*           It will be copied by [BeanUtils] to tranform to other layers
*/
    @PostMapping("/insert")
    public Result insert(@RequestBody BookInsertForm form) throws Exception {
        BookInsertParam param = new BookInsertParam();
        BeanUtils.copyProperties(form, param);
        bookService.insert(param);
        return Result.success("200", null);
    }

/**
* @method_3 Modify a book's information
* @return message only
* @param BookModifyForm
* @apiNote This method will receive the form from Front-end,
*          Only some of the information can be modified, as it depends on the Front-end form.
*/ 
    @PatchMapping("/modify")
    public Result modify(@RequestBody BookModifyForm form){
        BookInsertParam param = new BookInsertParam();
        BeanUtils.copyProperties(form, param);
        bookService.modify(param);
        return Result.success("200",null);
    }

    /**
     * @method_4: delete a book
     * @param bookId
     * @return message only
     * @throws Exception
     * @apiNote This function will not actually 'DELETE' data from the database,
     *          but rather modify the "available" field in the "book" table to mark a
     *          book as "deleted",
     *          and then filter out the marked books when querying all books again.
     *          This method is used to preserve historical records.
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
