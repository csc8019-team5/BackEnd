package uk.ac.ncl.team5project.service.Impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.ncl.team5project.param.BookInsertParam;
import uk.ac.ncl.team5project.entity.Book;
import uk.ac.ncl.team5project.entity.BookExample;
import uk.ac.ncl.team5project.mapper.BookMapper;
import uk.ac.ncl.team5project.service.BookService;
/**
 * @file BookServiceImpl.java
 * @date 2025-04-13 15:13
 * @function_description: All functions of books (CRUD) implement class.
 * @discussion: All methods will be implemented here; 
 *              they will correspond to their respective `Mapper` classes
 *              to operate on the database.
 * 
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: 
 *     @review_date: 
 *     @modification_date: 
 *     @description: 
 */

 @Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper bookMapper;

//METHOD 1: Qurey and display all books
    @Override
    public List<Book> loadBooks() {
        // TODO Auto-generated method stub
        BookExample bookExample = new BookExample();
        bookExample.createCriteria();
        List<Book> books = bookMapper.selectByExample(bookExample);
        return books;
    }

//METHOD 2: Add a new book
    @Override
    public void insert(BookInsertParam param) throws Exception {
        // TODO Auto-generated method stub
       // Check repetitions
       BookExample example = new BookExample();
       example.createCriteria().andNameEqualTo(param.getName());
        if (bookMapper.selectByExample(example).size()>0) {
            throw new Exception("This book already exist!");
        }
        // Insert a new book
        param.setAvailable(true);
        Book book = new Book();
        BeanUtils.copyProperties(param, book);
        bookMapper.insert(book);
    }

//METHOD 3: Modify the book's information
    @Override
    public void modify(BookInsertParam param) {
        // TODO Auto-generated method stub
        Book book = new Book();
        BeanUtils.copyProperties(param, book);
        bookMapper.updateByPrimaryKeySelective(book);
    }

//METHOD 4: Delete a book 
    @Override
    public void delete(Integer bookId) {
        // TODO Auto-generated method stub
        Book book = new Book();
        book.setBookId(bookId);            //Find the specific book by book ID
        book.setAvailable(false);   //Change the book's state to 'delete' it
        bookMapper.updateByPrimaryKeySelective(book);
    }



}