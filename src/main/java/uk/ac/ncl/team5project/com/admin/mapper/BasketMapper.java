package uk.ac.ncl.team5project.com.admin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import uk.ac.ncl.team5project.com.admin.entity.Basket;
import uk.ac.ncl.team5project.com.admin.entity.BasketExample;

public interface BasketMapper {
    long countByExample(BasketExample example);

    int deleteByExample(BasketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Basket row);

    int insertSelective(Basket row);

    List<Basket> selectByExample(BasketExample example);

    Basket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Basket row, @Param("example") BasketExample example);

    int updateByExample(@Param("row") Basket row, @Param("example") BasketExample example);

    int updateByPrimaryKeySelective(Basket row);

    int updateByPrimaryKey(Basket row);
}