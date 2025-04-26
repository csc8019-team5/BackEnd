package uk.ac.ncl.team5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.ac.ncl.team5project.entity.Loan;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    // 根据用户ID查正在借阅的书（未归还，未过期）
    List<Loan> findByUserIdAndReturnDateIsNull(Integer userId);

    // 查找某个用户的全部借阅记录（包括已归还的）
    List<Loan> findByUserId(Integer userId);

    // 查找所有状态为 active 的借阅记录（用于定时任务）
    List<Loan> findByReturnDateIsNull();

    
    // 查找已归还的借阅记录
    List<Loan> findByReturnDateIsNotNull();

    // 查找已过期但未归还的借阅记录
    @Query("SELECT l FROM Loan l WHERE l.returnDate IS NULL AND l.returnDateEstimated < CURRENT_DATE")
    List<Loan> findExpiredLoans();
}
