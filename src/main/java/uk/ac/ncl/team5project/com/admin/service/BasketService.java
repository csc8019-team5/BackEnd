package uk.ac.ncl.team5project.com.admin.service;

import java.util.List;

import uk.ac.ncl.team5project.com.admin.Param.BasketInfoParam;
import uk.ac.ncl.team5project.com.admin.entity.Basket;

public interface BasketService {

    List<BasketInfoParam> loadBasket(Integer userId) throws Exception;

    void insert(Integer bookId, Integer userId);

    List<Basket> check(Integer bookId, Integer userId);

    void delete(Integer userId, Integer bookId);

    
} 