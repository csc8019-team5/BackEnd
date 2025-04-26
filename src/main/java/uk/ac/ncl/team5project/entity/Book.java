package uk.ac.ncl.team5project.entity;

import jakarta.persistence.*;
import lombok.Data;

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
}
