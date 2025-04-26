package uk.ac.ncl.team5project.controller;

import uk.ac.ncl.team5project.entity.Book;
import uk.ac.ncl.team5project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * GET /v1/books
     * 获取书籍列表，支持分页、搜索、出版社分类
     */
    @GetMapping
    public Page<Book> getBooks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String publishingHouse
    ) {
        return bookService.getBooks(page, perPage, search, publishingHouse);
    }

    /**
     * GET /v1/books/{id}
     * 获取某本书的详细信息
     */
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }
}
