package uk.ac.ncl.team5project.service;

import uk.ac.ncl.team5project.entity.Book;
import uk.ac.ncl.team5project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Class: BookService
 * File: BookService.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Manages the electronic book catalog, providing functionalities to search, filter,
 *               and retrieve book information. Supports book metadata access for the library system.
 *     Interface Description:
 *         - getBooks: Retrieves a paginated list of available books with optional filtering.
 *         - getBookById: Fetches detailed information about a specific book.
 *     Calling Sequence:
 *         - Get Books List: Page<Book> books = bookService.getBooks(1, 10, "Java", "O'Reilly");
 *         - Get Single Book: Book book = bookService.getBookById(1);
 *     Argument Description:
 *         - page: The page number for pagination (starting from 1).
 *         - perPage: Number of books per page.
 *         - search: Optional search query to filter books by name.
 *         - publishingHouse: Optional filter for books from a specific publisher.
 *         - id: The unique identifier of a specific book.
 *     List of Subordinate Classes: Book, BookRepository.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Implemented search and filtering functionality with pagination support.
 * </pre>
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Retrieves a list of available books with optional pagination, keyword search, and publisher filter.
     *
     * @param page The page number to retrieve (starting from 1)
     * @param perPage Number of books per page
     * @param search Optional keyword for fuzzy title search
     * @param publishingHouse Optional filter for a specific publishing house
     * @return A page of books matching the given filters
     */

    public Page<Book> getBooks(int page, int perPage, String search, String publishingHouse) {
        PageRequest pageable = PageRequest.of(page - 1, perPage);
        int available = 1; // 只查可用图书

        if (search != null && !search.isEmpty() && publishingHouse != null && !publishingHouse.isEmpty()) {
            return bookRepository.findByNameContainingAndPublishingHouseAndAvailable(search, publishingHouse, available, pageable);
        } else if (search != null && !search.isEmpty()) {
            return bookRepository.findByNameContainingAndAvailable(search, available, pageable);
        } else if (publishingHouse != null && !publishingHouse.isEmpty()) {
            return bookRepository.findByPublishingHouseAndAvailable(publishingHouse, available, pageable);
        } else {
            return bookRepository.findByAvailable(available, pageable);
        }
    }

    /**
     * Retrieves detailed information of a book by its ID.
     * Throws exception if the book does not exist or is unavailable.
     *
     * @param id The ID of the book to retrieve
     * @return The book object
     * @throws RuntimeException if the book is not found or not available
     */

    public Book getBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can not find this book"));

        // Only return available book
        if (book.getAvailable() == null || book.getAvailable() != 1) {
            throw new RuntimeException("This book has been removed or is unavailable");
        }

        return book;
    }
}
