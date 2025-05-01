package uk.ac.ncl.team5project.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import uk.ac.ncl.team5project.entity.UserBook;
import uk.ac.ncl.team5project.entity.UserBookExample;

public interface UserBookMapper {
    long countByExample(UserBookExample example);

    int deleteByExample(UserBookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBook row);

    int insertSelective(UserBook row);

    List<UserBook> selectByExample(UserBookExample example);

    UserBook selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") UserBook row, @Param("example") UserBookExample example);

    int updateByExample(@Param("row") UserBook row, @Param("example") UserBookExample example);

    int updateByPrimaryKeySelective(UserBook row);

    int updateByPrimaryKey(UserBook row);
}