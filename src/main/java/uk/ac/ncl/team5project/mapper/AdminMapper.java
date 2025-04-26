package uk.ac.ncl.team5project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import uk.ac.ncl.team5project.entity.Admin;
import uk.ac.ncl.team5project.entity.User;


@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
