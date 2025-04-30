package uk.ac.ncl.team5project.controller;

import uk.ac.ncl.team5project.entity.jpa.Book;
import uk.ac.ncl.team5project.service.BookService;
import uk.ac.ncl.team5project.config.Result;
import uk.ac.ncl.team5project.model.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

@Slf4j
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
    public ResponseEntity<?> getBooks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String publishingHouse
    ) {
        try {
            log.info("Fetching books - page: {}, perPage: {}, search: {}, publishingHouse: {}", 
                     page, perPage, search, publishingHouse);
            
            if (page < 1) {
                return ResponseEntity.badRequest().body(Result.error(400, "Page number must be greater than 0"));
            }
            if (perPage < 1 || perPage > 100) {
                return ResponseEntity.badRequest().body(Result.error(400, "Items per page must be between 1 and 100"));
            }
            
            Page<Book> result = bookService.getBooks(page, perPage, search, publishingHouse);
            log.info("Found {} books, total {} pages", result.getTotalElements(), result.getTotalPages());
            
            // 转换为BookVO
            List<BookVO> bookVOs = result.getContent().stream()
                    .map(BookVO::toBookVO)
                    .collect(Collectors.toList());
            
            // 组装API文档格式的返回
            Map<String, Object> response = new HashMap<>();
            response.put("total", result.getTotalElements());
            response.put("page", page);
            response.put("per_page", perPage);
            response.put("books", bookVOs);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching books: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.error(500, "Failed to fetch book list: " + e.getMessage()));
        }
    }

    /**
     * Retrieves detailed information about a specific book by ID.
     *
     * @param id The ID of the book to retrieve
     * @return Book object with detailed information
     */

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        try {
            Book book = bookService.getBookById(id);
            BookVO bookVO = BookVO.toBookVO(book);
            return ResponseEntity.ok(bookVO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Result.error(404, e.getMessage()));
        }
    }

    @GetMapping("/categories")
    public Result getCategories() {
        try {
            log.info("Fetching all book categories");
            List<String> categories = bookService.getAllCategories();
            log.info("Found {} categories", categories.size());
            return Result.success(categories);
        } catch (Exception e) {
            log.error("Error fetching categories: {}", e.getMessage(), e);
            return Result.error(500, "Failed to fetch book categories: " + e.getMessage());
        }
    }

    @GetMapping("/categories/stats")
    public Result getCategoryStats() {
        try {
            log.info("Fetching category statistics");
            Map<String, Long> stats = bookService.getCategoryStats();
            log.info("Found stats for {} categories", stats.size());
            return Result.success(stats);
        } catch (Exception e) {
            log.error("Error fetching category statistics: {}", e.getMessage(), e);
            return Result.error(500, "Failed to fetch category statistics: " + e.getMessage());
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getBooksByCategory(
        @PathVariable String category,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(name = "per_page", defaultValue = "10") int perPage,
        @RequestParam(required = false) String search,
        @RequestParam(required = false) String publishingHouse
    ) {
        try {
            log.info("Fetching books by category: {} - page: {}, perPage: {}, search: {}, publishingHouse: {}", 
                     category, page, perPage, search, publishingHouse);
            
            if (page < 1) {
                return ResponseEntity.badRequest().body(Result.error(400, "Page number must be greater than 0"));
            }
            if (perPage < 1 || perPage > 100) {
                return ResponseEntity.badRequest().body(Result.error(400, "Items per page must be between 1 and 100"));
            }
            
            Page<Book> result = 
                bookService.getBooksByCategory(page, perPage, category, search, publishingHouse);
            log.info("Found {} books in category {}, total {} pages", 
                     result.getTotalElements(), category, result.getTotalPages());
            
            // 转换为BookVO
            List<BookVO> bookVOs = result.getContent().stream()
                    .map(BookVO::toBookVO)
                    .collect(Collectors.toList());
            
            // 组装API文档格式的返回
            Map<String, Object> response = new HashMap<>();
            response.put("total", result.getTotalElements());
            response.put("page", page);
            response.put("per_page", perPage);
            response.put("books", bookVOs);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching books by category: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.error(500, "Failed to fetch books by category: " + e.getMessage()));
        }
    }
}
