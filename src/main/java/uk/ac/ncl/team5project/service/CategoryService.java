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
     * Calculates category counts and relative weights for visualization.
     * 
     * @return List of maps containing category name, count, and weight
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
     * Provides total book count and percentage breakdown by category.
     * 
     * @return Map containing total books count and category distribution
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
