package uk.ac.ncl.team5project.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uk.ac.ncl.team5project.entity.jpa.Book;

/**
 * Class: BookVO
 * File: BookVO.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Value Object class for Book entity, used for transferring book data between
 *               the service layer and controller layer in response to client requests.
 *               Provides a simplified and formatted view of book data for API responses.
 *     Interface Description:
 *         - toBookVO: Static factory method that converts a Book entity to BookVO.
 *     Argument Description:
 *         - id: Unique identifier for the book.
 *         - title: Book title.
 *         - author: Book author name.
 *         - category: Book category/genre.
 *         - description: Book description or summary.
 *         - coverUrl: URL to the book cover image.
 *         - availableCopies: Number of available copies of the book.
 *         - loanDuration: Default loan duration in days.
 *     List of Subordinate Classes: None.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Initial implementation with Book entity conversion support.
 * </pre>
 */
@Data
public class BookVO {
    private Integer id;
    private String title;
    private String author;
    private String category;
    private String description;
    @JsonProperty("cover_url")
    private String coverUrl;
    @JsonProperty("available_copies")
    private Integer availableCopies;
    @JsonProperty("loan_duration")
    private Integer loanDuration;

    
    /**
     * Converts a Book entity to BookVO for API responses
     * 
     * @param book The Book entity to convert
     * @return The converted BookVO object or null if the input is null
     */
    public static BookVO toBookVO(Book book) {
        if (book == null) return null;
        BookVO vo = new BookVO();
        vo.setId(book.getId());
        vo.setTitle(book.getName());
        vo.setAuthor(book.getAuthor());
        vo.setCategory(book.getCategory());
        vo.setDescription(book.getDescription());
        vo.setCoverUrl(book.getBookCover());
        vo.setAvailableCopies(book.getAvailableNumber());
        vo.setLoanDuration(14); 
        return vo;
    }
}
