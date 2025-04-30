package uk.ac.ncl.team5project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import uk.ac.ncl.team5project.entity.Admin;
import uk.ac.ncl.team5project.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import uk.ac.ncl.team5project.entity.Admin;
import uk.ac.ncl.team5project.entity.AdminExample;


@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin row);

    int insertSelective(Admin row);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByExampleSelective(@Param("row") Admin row, @Param("example") AdminExample example);

    int updateByExample(@Param("row") Admin row, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin row);

    int updateByPrimaryKey(Admin row);
}
