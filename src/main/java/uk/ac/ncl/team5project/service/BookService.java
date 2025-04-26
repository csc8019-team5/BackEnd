package uk.ac.ncl.team5project.service;

import uk.ac.ncl.team5project.entity.Book;
import uk.ac.ncl.team5project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 获取书籍列表：分页 + 搜索 + 出版社筛选 + 只查可用图书
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
     * 获取某本书的详情
     */
    public Book getBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("找不到这本书"));

        // 只返回可用图书
        if (book.getAvailable() == null || book.getAvailable() != 1) {
            throw new RuntimeException("这本书已被删除或不可用");
        }

        return book;
    }
}
