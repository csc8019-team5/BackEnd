package uk.ac.ncl.team5project.controller;

import uk.ac.ncl.team5project.entity.jpa.Book;
import uk.ac.ncl.team5project.service.BookService;
import uk.ac.ncl.team5project.config.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
/**
 * Class: BookController
 * File: BookController.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Provides RESTful endpoints for querying book catalog. Supports operations
 *               such as listing books with pagination and filters, and retrieving individual book details.
 *     Interface Description:
 *         - getBooks: Retrieves a paginated list of books, optionally filtered by keyword and publisher.
 *         - getBookById: Retrieves detailed information about a specific book by ID.
 *     Calling Sequence:
 *         - GET /v1/books
 *         - GET /v1/books/{id}
 *     Argument Description:
 *         - page: Page number to retrieve.
 *         - perPage: Number of books per page.
 *         - search: Optional keyword for fuzzy search on title.
 *         - publishingHouse: Optional filter by publisher name.
 *         - id: Book ID path parameter.
 *     List of Subordinate Classes: BookService.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Initial implementation with paginated catalog support.
 * </pre>
 */

@RestController
@RequestMapping("/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Retrieves a paginated list of books.
     * Supports optional filtering by keyword and publishing house.
     *
     * @param page Page number to retrieve (default is 1)
     * @param perPage Number of books per page (default is 10)
     * @param search Optional keyword for fuzzy search
     * @param publishingHouse Optional filter by publishing house
     * @return A page of books matching the query
     */

    @GetMapping
    public Result getBooks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String publishingHouse
    ) {
        return Result.success(bookService.getBooks(page, perPage, search, publishingHouse));
    }

    /**
     * Retrieves detailed information about a specific book by ID.
     *
     * @param id The ID of the book to retrieve
     * @return Book object with detailed information
     */

    @GetMapping("/{id}")
    public Result getBookById(@PathVariable Integer id) {
        try {
            return Result.success(bookService.getBookById(id));
        } catch (RuntimeException e) {
            return Result.error(404, e.getMessage());
        }
    }
}
