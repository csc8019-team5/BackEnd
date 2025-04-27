package uk.ac.ncl.team5project.service;

import java.util.List;

import uk.ac.ncl.team5project.param.BookInsertParam;
import uk.ac.ncl.team5project.entity.Book;

/**
 * @file BookService.java
 * @date 2025-04-13 15:12
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

public interface BookService {

    List<Book> loadBooks();

    void insert(BookInsertParam param) throws Exception;

    void modify(BookInsertParam param);

    void delete(Integer bookId);


}
