package uk.ac.ncl.team5project.service.book;

import org.springframework.data.domain.Page;
import uk.ac.ncl.team5project.entity.jpa.Book;

/**
 * Book Query Service
 * Provides various query functions for books
 * Author: Yixin Zhang
 */

/**
 * Paginated query for books
 */

/**
 * Get details of a single book
 */
public interface BookQueryService {
    /**
     * Paginated query for books
     */
    Page<Book> getBooks(int page, int perPage, String search, String publishingHouse);

    /**
     * Get details of a single book
     */
    Book getBookById(Integer id);
} 