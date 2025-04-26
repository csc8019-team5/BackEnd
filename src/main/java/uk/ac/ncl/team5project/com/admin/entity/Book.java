package uk.ac.ncl.team5project.com.admin.entity;

public class Book {
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