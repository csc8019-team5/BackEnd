package uk.ac.ncl.team5project.com.admin.Param;

import lombok.Data;

@Data
public class BasketInfoParam {
    private Integer userId;
    private Integer bookId;
    private String name;
    private String category;
    private String author;
    private String publishingHouse;
    private String description;
    private String bookCover;
    private Boolean available;
}
