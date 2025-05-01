package uk.ac.ncl.team5project.service;

import java.util.List;

import uk.ac.ncl.team5project.param.BasketInfoParam;
import uk.ac.ncl.team5project.param.BasketPaidParam;
import uk.ac.ncl.team5project.entity.Basket;

public interface BasketService {

    List<BasketInfoParam> loadBasket(Integer userId) throws Exception;

    void insert(Integer bookId, Integer userId);

    List<Basket> check(Integer bookId, Integer userId);

    void delete(Integer userId, Integer bookId);

    void pay(BasketPaidParam bpp);

    
} 