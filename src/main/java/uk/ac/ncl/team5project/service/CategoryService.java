package uk.ac.ncl.team5project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ncl.team5project.repository.BookRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private BookRepository bookRepository;

    // 获取词云数据
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

    // 获取分布统计数据
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
