package uk.ac.ncl.team5project.mapper;

import org.apache.ibatis.annotations.Mapper;
import uk.ac.ncl.team5project.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import uk.ac.ncl.team5project.entity.User;
import uk.ac.ncl.team5project.entity.UserExample;


@Mapper
public interface UserMapper extends BaseMapper<User> {


    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User row);

    int insertSelective(User row);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("row") User row, @Param("example") UserExample example);

    int updateByExample(@Param("row") User row, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);
}
