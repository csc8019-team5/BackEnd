package uk.ac.ncl.team5project.service.impl;

import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
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
        // Create a sort object, sort by bookId in descending order
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // Create a pagination request object, note that page numbers start from 0
        Pageable pageable = PageRequest.of(page - 1, perPage, sort);
        
        try {
            Page<uk.ac.ncl.team5project.entity.jpa.Book> result;
            if (search != null && publishingHouse != null) {
                result = bookRepository.findByNameContainingAndPublishingHouseAndAvailable(search, publishingHouse, true, pageable);
            } else if (search != null) {
                result = bookRepository.findByNameContainingAndAvailable(search, true, pageable);
            } else if (publishingHouse != null) {
                result = bookRepository.findByPublishingHouseAndAvailable(publishingHouse, true, pageable);
            } else {
                result = bookRepository.findByAvailable(true, pageable);
            }
            
            // If the current page is greater than the total number of pages, return the last page
            if (result.getTotalPages() > 0 && page > result.getTotalPages()) {
                pageable = PageRequest.of(result.getTotalPages() - 1, perPage, sort);
                if (search != null && publishingHouse != null) {
                    result = bookRepository.findByNameContainingAndPublishingHouseAndAvailable(search, publishingHouse, true, pageable);
                } else if (search != null) {
                    result = bookRepository.findByNameContainingAndAvailable(search, true, pageable);
                } else if (publishingHouse != null) {
                    result = bookRepository.findByPublishingHouseAndAvailable(publishingHouse, true, pageable);
                } else {
                    result = bookRepository.findByAvailable(true, pageable);
                }
            }
            
            return result;
        } catch (Exception e) {
            log.error("Error fetching books: {}", e.getMessage());
            return Page.empty();
        }
    }

    @Override
    public uk.ac.ncl.team5project.entity.jpa.Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public Page<uk.ac.ncl.team5project.entity.jpa.Book> getBooksByCategory(
        int page, int perPage, String category, String search, String publishingHouse) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, perPage, sort);
        
        try {
            Page<uk.ac.ncl.team5project.entity.jpa.Book> result;
            if (search != null && publishingHouse != null) {
                result = bookRepository.findByCategoryAndNameContainingAndPublishingHouseAndAvailable(
                    category, search, publishingHouse, true, pageable);
            } else if (search != null) {
                result = bookRepository.findByCategoryAndNameContainingAndAvailable(
                    category, search, true, pageable);
            } else if (publishingHouse != null) {
                result = bookRepository.findByCategoryAndPublishingHouseAndAvailable(
                    category, publishingHouse, true, pageable);
            } else {
                result = bookRepository.findByCategoryAndAvailable(category, true, pageable);
            }
            
            // Handle page number out of bounds
            if (result.getTotalPages() > 0 && page > result.getTotalPages()) {
                pageable = PageRequest.of(result.getTotalPages() - 1, perPage, sort);
                if (search != null && publishingHouse != null) {
                    result = bookRepository.findByCategoryAndNameContainingAndPublishingHouseAndAvailable(
                        category, search, publishingHouse, true, pageable);
                } else if (search != null) {
                    result = bookRepository.findByCategoryAndNameContainingAndAvailable(
                        category, search, true, pageable);
                } else if (publishingHouse != null) {
                    result = bookRepository.findByCategoryAndPublishingHouseAndAvailable(
                        category, publishingHouse, true, pageable);
                } else {
                    result = bookRepository.findByCategoryAndAvailable(category, true, pageable);
                }
            }
            
            return result;
        } catch (Exception e) {
            log.error("Error fetching books by category: {}", e.getMessage());
            return Page.empty();
        }
    }

    @Override
    public List<String> getAllCategories() {
        try {
            return bookRepository.findAllCategories();
        } catch (Exception e) {
            log.error("Error fetching categories: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Map<String, Long> getCategoryStats() {
        try {
            List<Object[]> stats = bookRepository.countBooksByCategory();
            return stats.stream()
                .collect(Collectors.toMap(
                    row -> (String) row[0],
                    row -> (Long) row[1],
                    (v1, v2) -> v1,
                    LinkedHashMap::new
                ));
        } catch (Exception e) {
            log.error("Error fetching category statistics: {}", e.getMessage());
            return Collections.emptyMap();
        }
    }
}