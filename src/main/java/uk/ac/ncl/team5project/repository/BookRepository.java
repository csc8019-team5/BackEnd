package uk.ac.ncl.team5project.repository;

import uk.ac.ncl.team5project.entity.jpa.Book;
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
 *     Modification Date: 27/04/2025
 *     Modification Description: Implemented repository with search, filtering and aggregation methods.
 * </pre>
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    /**
     * Performs a fuzzy search on book names, returning only available books.
     * Useful for search functionality in the catalog browsing interface.
     *
     * @param name String fragment to search for in book titles
     * @param available Availability status to filter by
     * @param pageable Pagination and sorting parameters
     * @return Paginated list of books matching the name fragment and availability status
     */
    Page<Book> findByNameContainingAndAvailable(String name, Boolean available, Pageable pageable);

    /**
     * Retrieves books published by a specific publishing house, filtered by availability.
     * Used for publisher-specific catalog views.
     *
     * @param publishingHouse Publisher name to filter by
     * @param available Availability status to filter by
     * @param pageable Pagination and sorting parameters
     * @return Paginated list of books from the specified publisher with given availability
     */
    Page<Book> findByPublishingHouseAndAvailable(String publishingHouse, Boolean available, Pageable pageable);

    /**
     * Combines name search and publisher filtering with availability check.
     * Provides more targeted search results when both criteria are specified.
     *
     * @param name String fragment to search for in book titles
     * @param publishingHouse Publisher name to filter by
     * @param available Availability status to filter by
     * @param pageable Pagination and sorting parameters
     * @return Paginated list of books matching all criteria
     */
    Page<Book> findByNameContainingAndPublishingHouseAndAvailable(String name, String publishingHouse, Boolean available, Pageable pageable);

    /**
     * Retrieves all books with a specific availability status.
     * Used to show either all available or all unavailable books.
     *
     * @param available Availability status to filter by
     * @param pageable Pagination and sorting parameters
     * @return Paginated list of books with the specified availability status
     */
    Page<Book> findByAvailable(Boolean available, Pageable pageable);

    /**
     * Retrieves books in a specific category, filtered by availability.
     * Used for category-specific browsing views.
     *
     * @param category Category name to filter by
     * @param available Availability status to filter by
     * @param pageable Pagination and sorting parameters
     * @return Paginated list of books in the specified category with given availability
     */
    Page<Book> findByCategoryAndAvailable(String category, Boolean available, Pageable pageable);

    /**
     * Performs a fuzzy search on book names within a specific category, filtered by availability.
     * Combines category browsing with search functionality.
     *
     * @param category Category name to filter by
     * @param name String fragment to search for in book titles
     * @param available Availability status to filter by
     * @param pageable Pagination and sorting parameters
     * @return Paginated list of books matching the category, name fragment, and availability
     */
    Page<Book> findByCategoryAndNameContainingAndAvailable(String category, String name, Boolean available, Pageable pageable);

    /**
     * Retrieves books in a specific category from a specific publisher, filtered by availability.
     * Combines category and publisher filtering.
     *
     * @param category Category name to filter by
     * @param publishingHouse Publisher name to filter by
     * @param available Availability status to filter by
     * @param pageable Pagination and sorting parameters
     * @return Paginated list of books matching the category, publisher, and availability
     */
    Page<Book> findByCategoryAndPublishingHouseAndAvailable(String category, String publishingHouse, Boolean available, Pageable pageable);

    /**
     * Performs a comprehensive search with category, name, publisher, and availability filters.
     * Provides the most specific search results when all criteria are specified.
     *
     * @param category Category name to filter by
     * @param name String fragment to search for in book titles
     * @param publishingHouse Publisher name to filter by
     * @param available Availability status to filter by
     * @param pageable Pagination and sorting parameters
     * @return Paginated list of books matching all specified criteria
     */
    Page<Book> findByCategoryAndNameContainingAndPublishingHouseAndAvailable(
            String category, String name, String publishingHouse, Boolean available, Pageable pageable);

    /**
     * Retrieves a distinct list of all categories for available books.
     * Used for populating category filter options in the UI.
     *
     * @return List of distinct category names for available books, sorted alphabetically
     */
    @Query("SELECT DISTINCT b.category FROM Book b WHERE b.available = true ORDER BY b.category")
    List<String> findAllCategories();

    /**
     * Counts the number of available books in each category.
     * Used for generating category statistics and visualizations.
     *
     * @return List of Object arrays where the first element is the category name
     *         and the second element is the count of books in that category
     */
    @Query("SELECT b.category, COUNT(b) FROM Book b WHERE b.available = true GROUP BY b.category ORDER BY b.category")
    List<Object[]> countBooksByCategory();

    /**
     * Retrieves books that have been borrowed by a specific user.
     * Uses a native SQL query to join the BOOK and USER_BOOK tables.
     *
     * @param userId The ID of the user whose borrowed books to retrieve
     * @return List of Book objects that the specified user has borrowed
     */
    @Query(value = "SELECT b.* FROM BOOK b " +
            "JOIN USER_BOOK ub ON b.book_id = ub.book_id " +
            "WHERE ub.user_id = :userId", nativeQuery = true)
    List<Book> findBooksByUserId1(@Param("userId") Integer userId);
}
