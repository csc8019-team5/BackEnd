package uk.ac.ncl.team5project.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uk.ac.ncl.team5project.param.BookInsertParam;
import uk.ac.ncl.team5project.entity.Book;
import uk.ac.ncl.team5project.entity.BookExample;
import uk.ac.ncl.team5project.mapper.BookMapper;
import uk.ac.ncl.team5project.repository.BookRepository;
import uk.ac.ncl.team5project.service.BookService;

/**
 * @file BookServiceImpl.java
 * @date 2025-04-13 15:13
 * @function_description: Implementation of BookService that handles both MyBatis and JPA operations
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> loadBooks() {
        BookExample bookExample = new BookExample();
        bookExample.createCriteria();
        return bookMapper.selectByExample(bookExample);
    }

    @Override
    public void insert(BookInsertParam param) throws Exception {
        BookExample example = new BookExample();
        example.createCriteria().andNameEqualTo(param.getName());
        if (bookMapper.selectByExample(example).size() > 0) {
            throw new Exception("This book already exists!");
        }
        param.setAvailable(true);
        Book book = new Book();
        BeanUtils.copyProperties(param, book);
        bookMapper.insert(book);
    }

    @Override
    public void modify(BookInsertParam param) {
        Book book = new Book();
        BeanUtils.copyProperties(param, book);
        bookMapper.updateByPrimaryKeySelective(book);
    }

    @Override
    public void delete(Integer bookId) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setAvailable(false);
        bookMapper.updateByPrimaryKeySelective(book);
    }

    @Override
    public Page<uk.ac.ncl.team5project.entity.jpa.Book> getBooks(int page, int perPage, String search, String publishingHouse) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        
        if (search != null && publishingHouse != null) {
            return bookRepository.findByNameContainingAndPublishingHouseAndAvailable(search, publishingHouse, true, pageable);
        } else if (search != null) {
            return bookRepository.findByNameContainingAndAvailable(search, true, pageable);
        } else if (publishingHouse != null) {
            return bookRepository.findByPublishingHouseAndAvailable(publishingHouse, true, pageable);
        } else {
            return bookRepository.findByAvailable(true, pageable);
        }
    }

    @Override
    public uk.ac.ncl.team5project.entity.jpa.Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }
}