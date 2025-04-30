package uk.ac.ncl.team5project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @file Book.java
 * @date 2025-04-01
 * @function_description: Entity class representing the BOOK table in the database.
 * @interface_description: Includes book ID, name, publisher, description, and cover image.
 * @calling_sequence: MyBatis-Plus → BookMapper → BOOK table
 * @arguments_description: None
 * @list_of_subordinate_classes: None
 * @discussion: Used for book catalog, wishlist, and borrowing modules.
 * @development_history: Created on 2025-04-01 as part of book entity setup.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Maps book-related fields to the BOOK database table.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "book_id", type = IdType.AUTO)
    private Integer bookId;

    private String name;

    private String category;

    private String author;

    private String publishingHouse;

    private String description;

    private String bookCover;

    private Boolean available;

    private String keyfeature;

    private Integer availableNumber;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getKeyfeature() {
        return keyfeature;
    }

    public void setKeyfeature(String keyfeature) {
        this.keyfeature = keyfeature;
    }

    public Integer getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(Integer availableNumber) {
        this.availableNumber = availableNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookId=").append(bookId);
        sb.append(", name=").append(name);
        sb.append(", category=").append(category);
        sb.append(", author=").append(author);
        sb.append(", publishingHouse=").append(publishingHouse);
        sb.append(", description=").append(description);
        sb.append(", bookCover=").append(bookCover);
        sb.append(", available=").append(available);
        sb.append(", keyfeature=").append(keyfeature);
        sb.append(", availableNumber=").append(availableNumber);
        sb.append("]");
        return sb.toString();
    }
}
