package uk.ac.ncl.team5project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ncl.team5project.entity.Loan;
import uk.ac.ncl.team5project.entity.Book;
import uk.ac.ncl.team5project.repository.LoanRepository;
import uk.ac.ncl.team5project.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private BookRepository bookRepository;

    /**
     * 3.1 借书
     */
    @Transactional
    public Loan borrowBook(Integer userId, Integer bookId, int borrowDurationDays) {
        // 检查图书是否存在且可用
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("图书不存在"));
                
        if (book.getAvailable() == null || book.getAvailable() != 1) {
            throw new RuntimeException("该图书不可借阅");
        }
        
        // 更新图书状态为不可用
        book.setAvailable(0);
        bookRepository.save(book);
        
        // 创建借阅记录
        Loan loan = new Loan();
        loan.setUserId(userId);
        loan.setBookId(bookId);
        loan.setBorrowDate(LocalDate.now());
        loan.setReturnDateEstimated(LocalDate.now().plusDays(borrowDurationDays));
        loan.setReturnDate(null);
        return loanRepository.save(loan);
    }

    /**
     * 3.2 当前正在借的书
     */
    public List<Loan> getCurrentLoans(Integer userId) {
        // 查询未归还的借阅记录
        return loanRepository.findByUserIdAndReturnDateIsNull(userId);
    }
    
    /**
     * 3.3 归还图书
     */
    @Transactional
    public Loan returnBook(Integer loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("借阅记录不存在"));
        
        // 如果已归还，直接返回
        if (loan.getReturnDate() != null) {
            return loan;
        }
        
        // 更新借阅记录
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        
        // 更新图书状态为可用
        Book book = bookRepository.findById(loan.getBookId())
                .orElseThrow(() -> new RuntimeException("图书不存在"));
        book.setAvailable(1);
        bookRepository.save(book);
        
        return loan;
    }
    
    /**
     * 3.4 借阅历史记录
     */
    public List<Loan> getLoanHistory(Integer userId) {
        // 查询所有已归还的借阅记录
        return loanRepository.findByUserId(userId);
    }
    
    /**
     * 3.5 查询所有借阅记录（为3.4提供支持）
     */
    public List<Loan> getAllLoans(Integer userId) {
        // 返回用户的所有借阅记录，包括当前和历史
        return loanRepository.findByUserId(userId);
    }
    
    /**
     * 3.6 自动将过期借阅设置为expired
     */
    @Transactional
    public void autoExpire() {
        List<Loan> activeLoans = loanRepository.findByReturnDateIsNull();
        LocalDate today = LocalDate.now();

        for (Loan loan : activeLoans) {
            if (loan.getReturnDateEstimated() != null && loan.getReturnDateEstimated().isBefore(today)) {
                // 更新借阅记录
                loan.setReturnDate(loan.getReturnDateEstimated()); // 简化处理，将归还日期设为预期日期
                loanRepository.save(loan);
                
                // 更新图书状态
                Book book = bookRepository.findById(loan.getBookId())
                        .orElseThrow(() -> new RuntimeException("图书不存在"));
                book.setAvailable(1);
                bookRepository.save(book);
            }
        }
    }
}
