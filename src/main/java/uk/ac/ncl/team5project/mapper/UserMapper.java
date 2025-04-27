package uk.ac.ncl.team5project.mapper;

import org.apache.ibatis.annotations.Mapper;
import uk.ac.ncl.team5project.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {

}
