package uk.ac.ncl.team5project.repository;

import uk.ac.ncl.team5project.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Class: BookRepository
 * File: BookRepository.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Provides data access operations for book records, extending JpaRepository
 *               with custom query methods for searching and filtering books in the catalog.
 *     Interface Description:
 *         - findByNameContainingAndAvailable: Searches books by name fragment and availability.
 *         - findByPublishingHouseAndAvailable: Filters books by publisher and availability.
 *         - findByNameContainingAndPublishingHouseAndAvailable: Combines name search and publisher filtering.
 *         - findByAvailable: Retrieves all books with a specific availability status.
 *         - countBooksByCategory: Aggregates books by category for visualization purposes.
 *     Calling Sequence:
 *         - Search Books: Page<Book> results = bookRepository.findByNameContainingAndAvailable("Java", 1, pageable);
 *         - Filter by Publisher: Page<Book> books = bookRepository.findByPublishingHouseAndAvailable("O'Reilly", 1, pageable);
 *         - Get All Available: Page<Book> available = bookRepository.findByAvailable(1, pageable);
 *         - Count By Category: List<Object[]> counts = bookRepository.countBooksByCategory();
 *     Argument Description:
 *         - name: String fragment to search for in book titles.
 *         - publishingHouse: Publisher name to filter books by.
 *         - available: Availability status (1=available, 0=unavailable).
 *         - pageable: Pagination and sorting parameters.
 *     List of Subordinate Classes: Book.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Implemented repository with search, filtering and aggregation methods.
 * </pre>
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Fuzzy search based on book name
    Page<Book> findByNameContainingAndAvailable(String name, Integer available, Pageable pageable);

    // Filter by publishing house
    Page<Book> findByPublishingHouseAndAvailable(String publishingHouse, Integer available, Pageable pageable);

    // Search by name and publishing house
    Page<Book> findByNameContainingAndPublishingHouseAndAvailable(String name, String publishingHouse, Integer available, Pageable pageable);

    // Fetch all available books
    Page<Book> findByAvailable(Integer available, Pageable pageable);


    @Query("SELECT b.category, COUNT(b) FROM Book b WHERE b.available = 1 GROUP BY b.category")
    List<Object[]> countBooksByCategory();
}
