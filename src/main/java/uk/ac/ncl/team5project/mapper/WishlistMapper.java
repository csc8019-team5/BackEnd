package uk.ac.ncl.team5project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import uk.ac.ncl.team5project.entity.Wishlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import uk.ac.ncl.team5project.model.vo.BookCount;

import java.util.List;

@Mapper

public interface WishlistMapper extends BaseMapper<Wishlist> {
    @Select("SELECT book_id AS bookId, COUNT(*) AS count FROM WISHLIST GROUP BY book_id;")
    List<BookCount> getBookCounts();
}
