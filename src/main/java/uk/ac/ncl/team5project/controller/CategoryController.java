package uk.ac.ncl.team5project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.ncl.team5project.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 9.1 标签云数据（返回 Map 格式）
    @GetMapping("/tagcloud")
    public Map<String, Object> getTagCloud() {
        Map<String, Object> result = new HashMap<>();
        result.put("categories", categoryService.getCategoryTagCloud());
        return result;
    }

    // 9.2 分类分布统计（原样返回）
    @GetMapping("/distribution")
    public Map<String, Object> getDistribution() {
        return categoryService.getCategoryDistribution();
    }
}
