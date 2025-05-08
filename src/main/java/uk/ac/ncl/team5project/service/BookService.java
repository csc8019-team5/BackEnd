package uk.ac.ncl.team5project.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import uk.ac.ncl.team5project.param.BookInsertParam;

/**
 * @file BookService.java
 * @date 2025-04-13 15:12
 * @function_description: Book service interface that handles both MyBatis and JPA operations
 * Author: Yixin Zhang
 */
public interface BookService {
    // MyBatis operations
    List<uk.ac.ncl.team5project.entity.Book> loadBooks();
    void insert(BookInsertParam param) throws Exception;
    void modify(BookInsertParam param);
    void delete(Integer bookId);

    // JPA operations
    Page<uk.ac.ncl.team5project.entity.jpa.Book> getBooks(int page, int perPage, String search, String publishingHouse);
    uk.ac.ncl.team5project.entity.jpa.Book getBookById(Integer id);

    // Category methods
    Page<uk.ac.ncl.team5project.entity.jpa.Book> getBooksByCategory(
        int page, int perPage, String category, String search, String publishingHouse);
    List<String> getAllCategories();
    Map<String, Long> getCategoryStats();

    List<uk.ac.ncl.team5project.entity.jpa.Book> getBooksByUserId1(Integer userId);
}
