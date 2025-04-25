package uk.ac.ncl.team5project.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Class: Book
 * File: Book.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Represents a book entity in the library system, containing all metadata about
 *               books including title, author, category, and availability status.
 *     Interface Description:
 *         - id (Integer): The unique identifier of the book.
 *         - name (String): The title of the book.
 *         - author (String): The author of the book.
 *         - category (String): The category/genre of the book.
 *         - publishingHouse (String): The publisher of the book.
 *         - description (String): A summary or description of the book.
 *         - bookCover (String): URL to the book cover image.
 *         - available (Integer): Flag indicating if the book is available (1) or not (0).
 *         - keyfeature (String): Key features or highlights of the book.
 *     Calling Sequence:
 *         - This entity class is used in database operations and API responses.
 *         - Retrieved through BookService for catalog display and loan processing.
 *     Argument Description:
 *         - id: Unique identifier assigned by the database.
 *         - name: Book title, displayed in the catalog.
 *         - author: Book author, displayed in the catalog.
 *         - category: Book category used for classification and visualization.
 *         - publishingHouse: Publisher information.
 *         - description: Detailed book description.
 *         - bookCover: URL to an external book cover image.
 *         - available: Status flag indicating borrowing availability.
 *         - keyfeature: Highlights or special features of the book.
 *     List of Subordinate Classes: None.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Initial implementation with updated field structure.
 * </pre>
 */
@Data
@Entity
@Table(name = "\"BOOK\"")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "publishing_house")
    private String publishingHouse;

    @Column(name = "description")
    private String description;

    @Column(name = "book_cover")
    private String bookCover;

    @Column(name = "available")
    private Integer available;

    @Column(name = "keyfeature")
    private String keyfeature;
}
