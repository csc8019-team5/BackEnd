package uk.ac.ncl.team5project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "\"USER_BOOK\"") 
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "return_date_estimated")
    private LocalDate returnDateEstimated;

    @Column(name = "return_date")
    private LocalDate returnDate;

    // 虚拟字段，用于判断状态：active / returned / expired
    @Transient
    public String getStatus() {
        if (returnDate != null) return "returned";
        if (returnDateEstimated != null && returnDateEstimated.isBefore(LocalDate.now())) return "expired";
        return "active";
    }

    @Transient
    public LocalDate getDueDate() {
        return returnDateEstimated;
    }

    @Transient
    public void setStatus(String status) {
        // 虚拟字段
    }

}
