package uk.ac.ncl.team5project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import uk.ac.ncl.team5project.entity.Wishlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import uk.ac.ncl.team5project.model.vo.BookCategoryCount;
import uk.ac.ncl.team5project.model.vo.BookCount;

import java.util.List;

@Mapper

public interface WishlistMapper extends BaseMapper<Wishlist> {
    @Select("SELECT COUNT(*) AS count, " +
            "BOOK.name AS bookName " +
            "FROM WISHLIST " +
            "JOIN BOOK ON WISHLIST.book_id = BOOK.book_id " +
            "GROUP BY BOOK.book_id, BOOK.name;")
    List<BookCount> getBookCounts();

    @Select("SELECT COUNT(*) AS count, " +
            "BOOK.category AS category " +
            "FROM WISHLIST " +
            "JOIN BOOK ON WISHLIST.book_id = BOOK.book_id " +
            "GROUP BY BOOK.book_id, BOOK.category;")
    List<BookCategoryCount> getBookCategoryCounts();
}