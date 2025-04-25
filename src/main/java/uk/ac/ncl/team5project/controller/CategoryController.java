package uk.ac.ncl.team5project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.ncl.team5project.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Class: CategoryController
 * File: CategoryController.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Provides endpoints for category-based visualizations such as tag clouds
 *               and category distribution statistics.
 *     Interface Description:
 *         - getTagCloud: Returns category frequency data for tag cloud generation.
 *         - getDistribution: Returns overall distribution of books across categories.
 *     Calling Sequence:
 *         - GET /v1/categories/tagcloud
 *         - GET /v1/categories/distribution
 *     Argument Description:
 *         - No request parameters.
 *     List of Subordinate Classes: CategoryService.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Added endpoints for tag cloud and distribution visualization.
 * </pre>
 */

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 9.1 Tag cloud data (returned as a Map)
    @GetMapping("/tagcloud")
    public Map<String, Object> getTagCloud() {
        Map<String, Object> result = new HashMap<>();
        result.put("categories", categoryService.getCategoryTagCloud());
        return result;
    }

    // 9.2 Category distribution statistics (returned as-is)
    @GetMapping("/distribution")
    public Map<String, Object> getDistribution() {
        return categoryService.getCategoryDistribution();
    }
}
