package uk.ac.ncl.team5project.repository;

import uk.ac.ncl.team5project.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface BookRepository extends JpaRepository<Book, Integer> {

    // 1. 根据 name 模糊搜索，并筛选可用图书
    Page<Book> findByNameContainingAndAvailable(String name, Integer available, Pageable pageable);

    // 2. 根据出版社筛选，并筛选可用图书
    Page<Book> findByPublishingHouseAndAvailable(String publishingHouse, Integer available, Pageable pageable);

    //  3. 搜索 + 出版社双条件，并筛选可用图书
    Page<Book> findByNameContainingAndPublishingHouseAndAvailable(String name, String publishingHouse, Integer available, Pageable pageable);

    //  4. 不加条件，只查所有可用图书
    Page<Book> findByAvailable(Integer available, Pageable pageable);


    @Query("SELECT b.category, COUNT(b) FROM Book b WHERE b.available = 1 GROUP BY b.category")
    List<Object[]> countBooksByCategory();
}
