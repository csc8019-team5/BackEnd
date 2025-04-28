package uk.ac.ncl.team5project.service.book;

import uk.ac.ncl.team5project.param.BookInsertParam;
import uk.ac.ncl.team5project.entity.Book;
import java.util.List;

/**
 * Book Management Service
 * Provides management functions for adding, deleting, updating, and querying books
 * Author: Yixin Zhang
 */
public interface BookManagementService {
    /**
     * Load all books
     */
    List<Book> loadBooks();

    /**
     * Add a new book
     */
    void insert(BookInsertParam param) throws Exception;

    /**
     * Modify book information
     */
    void modify(BookInsertParam param);

    /**
     * Delete a book (soft delete)
     */
    void delete(Integer bookId);
} 