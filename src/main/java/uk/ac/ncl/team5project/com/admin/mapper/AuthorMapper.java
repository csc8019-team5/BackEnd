package uk.ac.ncl.team5project.com.admin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import uk.ac.ncl.team5project.com.admin.entity.Author;
import uk.ac.ncl.team5project.com.admin.entity.AuthorExample;

public interface AuthorMapper {
    long countByExample(AuthorExample example);

    int deleteByExample(AuthorExample example);

    int deleteByPrimaryKey(Integer authorId);

    int insert(Author row);

    int insertSelective(Author row);

    List<Author> selectByExample(AuthorExample example);

    Author selectByPrimaryKey(Integer authorId);

    int updateByExampleSelective(@Param("row") Author row, @Param("example") AuthorExample example);

    int updateByExample(@Param("row") Author row, @Param("example") AuthorExample example);

    int updateByPrimaryKeySelective(Author row);

    int updateByPrimaryKey(Author row);
}