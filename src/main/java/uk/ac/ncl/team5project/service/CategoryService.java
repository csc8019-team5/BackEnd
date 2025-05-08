package uk.ac.ncl.team5project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ncl.team5project.repository.BookRepository;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class: CategoryService
 * File: CategoryService.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Provides book category visualization data, including tag cloud information and
 *               category distribution statistics for the library management system.
 *     Interface Description:
 *         - getCategoryTagCloud: Generates tag cloud data for book categories.
 *         - getCategoryDistribution: Calculates statistical distribution of books across categories.
 *     Calling Sequence:
 *         - Get Tag Cloud: List<Map<String, Object>> tagCloud = categoryService.getCategoryTagCloud();
 *         - Get Distribution: Map<String, Object> distribution = categoryService.getCategoryDistribution();
 *     Argument Description:
 *         - No input parameters for either method.
 *         - Return values include category names, book counts, weights and percentages.
 *     List of Subordinate Classes: BookRepository.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Implemented category visualization features for Module 9.
 * </pre>
 */
@Service
public class CategoryService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Generates tag cloud data for book categories.
     *
     * This method queries the database for all available book categories and their counts,
     * then calculates relative weights for each category based on its proportion of total books.
     * The resulting data structure is designed for rendering tag clouds in the UI, where
     * categories with higher weights can be displayed more prominently.
     *
     * The weight calculation formula is: weight = categoryCount / totalBooks
     *
     * @return List of maps containing category information with the following keys:
     *         - "name": The category name (String)
     *         - "count": Number of books in the category (Integer)
     *         - "weight": Relative weight of the category (Double between 0 and 1)
     * @see BookRepository#countBooksByCategory() The underlying data source method
     */
    public List<Map<String, Object>> getCategoryTagCloud() {
        List<Object[]> result = bookRepository.countBooksByCategory();
        int total = result.stream().mapToInt(row -> ((Number) row[1]).intValue()).sum();


        return result.stream().map(row -> {
            String category = (String) row[0];
            int count = ((Number) row[1]).intValue();
            double weight = total == 0 ? 0 : (double) count / total;
            Map<String, Object> map = new HashMap<>();
            map.put("name", category);
            map.put("count", count);
            map.put("weight", weight);
            return map;
        }).collect(Collectors.toList());
    }

    /**
     * Calculates distribution statistics for book categories.
     *
     * This method provides a comprehensive breakdown of how books are distributed
     * across different categories in the library system. It queries the database for
     * category counts, calculates the percentage for each category relative to the total
     * book count, and returns a structured data set suitable for statistical analysis
     * and visualization (e.g., pie charts, bar graphs).
     *
     * The percentage calculation formula is: percentage = (categoryCount / totalBooks) * 100
     *
     * @return Map containing two elements:
     *         - "total_books": Total number of available books in the system (Integer)
     *         - "distribution": List of maps, each containing:
     *             - "category": The category name (String)
     *             - "book_count": Number of books in the category (Integer)
     *             - "percentage": Percentage of total books in this category (Double)
     * @see BookRepository#countBooksByCategory() The underlying data source method
     */
    public Map<String, Object> getCategoryDistribution() {
        List<Object[]> result = bookRepository.countBooksByCategory();
        int total = result.stream().mapToInt(row -> ((Number) row[1]).intValue()).sum();


        List<Map<String, Object>> distribution = result.stream().map(row -> {
            String category = (String) row[0];
            int count = ((Number) row[1]).intValue();
            double percentage = total == 0 ? 0 : (count * 100.0 / total);


            Map<String, Object> map = new HashMap<>();
            map.put("category", category);
            map.put("book_count", count);
            map.put("percentage", percentage);
            return map;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("total_books", total);
        response.put("distribution", distribution);
        return response;
    }
}
