package uk.ac.ncl.team5project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.ncl.team5project.param.BasketInfoParam;
import uk.ac.ncl.team5project.entity.Basket;
import uk.ac.ncl.team5project.entity.BasketExample;
import uk.ac.ncl.team5project.entity.Book;
import uk.ac.ncl.team5project.entity.BookExample;
import uk.ac.ncl.team5project.mapper.BasketMapper;
import uk.ac.ncl.team5project.mapper.BookMapper;
import uk.ac.ncl.team5project.service.BasketService;

/**
 * @file BasketServiceImpl.java
 * @date 2025-04-19 
 * @function_description: Service implementation for `Basket` 
 * @interface_description:
 * - Query all books in user's basket
 * - Add the book into user's basket
 * - Delete the book from user's basket
 * - Check the book if already in the basket. If so, the `add to basket` button should change.
 * @discussion: Require authentication before loading this page.
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: Qingyu Cao 
 *     @review_date: 2025-04-27
 *     @modification_date: 2025-04-27
 *     @description: 
 *      - Add `Check` method for button
 */


@Service
public class BasketServiceImpl implements BasketService{

    @Autowired
    private BasketMapper basketMapper;
    @Autowired
    private BookMapper bookMapper;

    /**
     * METHOD 1: Qurey books by userId with valid
     * @param userId
     * @return List<Basket> 
     * @throws Exception 
     * @apiNote This api will display all books in basket, 
     *          queried by user's ID and the filed 'valid' should be '1',
     *          the `valid` with `1` means books in user's basket,
     *          `valid` with `0` means books are accessible.
     */
    @Override
    public List<BasketInfoParam> loadBasket(Integer userId) throws Exception {
        // TODO Auto-generated method stub
        BasketExample basketExample = new BasketExample();
        basketExample.createCriteria().andIsValidEqualTo(1).andUserIdEqualTo(userId);
        List<Basket> baskets = basketMapper.selectByExample(basketExample);
        if (baskets.size()>0) {
            List<Book> books = new ArrayList<>();
            BookExample bookExample = new BookExample();
            for(Basket basket : baskets){
                bookExample.createCriteria().andBookIdEqualTo(basket.getBookId());
                Book book = bookMapper.selectByExample(bookExample).get(0);
                books.add(book);
            }
            List<BasketInfoParam> basketInfoParams = new ArrayList<>();
            for (Book book : books) {
                BasketInfoParam param = new BasketInfoParam();
                BeanUtils.copyProperties(book, param); 
                param.setUserId(userId);              
                basketInfoParams.add(param);           
            }
            return basketInfoParams;
        }else{
            throw new Exception("The basket is empty");
        }
    }

    /**
     * METHOD 2: Check the book if is already put into basket
     * @param bookId,userId
     * @return List<Basket>
     * @apiNote This api is for checking the book if already put into the basket,
     *          if so, the button `Add to basket` should be changed.
     */
    @Override
    public List<Basket> check(Integer bookId, Integer userId) {
        // TODO Auto-generated method stub
        BasketExample basketExample = new BasketExample();
        basketExample.createCriteria().andIsValidEqualTo(1).andUserIdEqualTo(userId).andBookIdEqualTo(bookId);
        return basketMapper.selectByExample(basketExample);
    }

    /**
     * METHOD 3: Insert the book into the basket
     * @param bookId,userId
     */
    @Override
    public void insert(Integer bookId, Integer userId) {
        // TODO Auto-generated method stub
        Basket basket = new Basket();
        basket.setUserId(userId);
        basket.setBookId(bookId);
        basketMapper.insert(basket);
    }

    /**
     * METHOD 4: Delete the book from basket
     * @param userId,bookId
     * @apiNote This delete api designed by change the book's status to delete it from
     *          basket. User select the book then change the field `is_valid` from database
     *          by sending the book's ID.
     */
    @Override
    public void delete(Integer userId, Integer bookId) {
        // TODO Auto-generated method stub
        BasketExample basketExample = new BasketExample();
        basketExample.createCriteria().andIsValidEqualTo(1).andUserIdEqualTo(userId).andBookIdEqualTo(bookId);
        Basket book = basketMapper.selectByExample(basketExample).get(0);
        book.setIsValid(0);
    }









}
