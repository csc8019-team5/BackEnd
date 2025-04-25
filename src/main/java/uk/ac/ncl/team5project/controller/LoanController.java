package uk.ac.ncl.team5project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.ncl.team5project.entity.Loan;
import uk.ac.ncl.team5project.service.LoanService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // 3.1 借书
    @PostMapping
    public Loan borrowBook(@RequestBody Map<String, Integer> request) {
        Integer userId = request.get("user_id");     // 后续可用 JWT 获取
        Integer bookId = request.get("book_id");
        int duration = 14; // 默认 14 天，可后续从配置表读取
        return loanService.borrowBook(userId, bookId, duration);
    }

    // 3.2 当前借阅列表
    @GetMapping("/current")
    public List<Loan> getCurrentLoans(@RequestParam Integer user_id) {
        return loanService.getCurrentLoans(user_id);
    }

    // 3.3 归还图书
    @PutMapping("/{loan_id}/return")
    public Loan returnBook(@PathVariable("loan_id") Integer loanId) {
        return loanService.returnBook(loanId);
    }

    // 3.4 借阅历史记录
    @GetMapping("/history")
    public List<Loan> getLoanHistory(@RequestParam Integer user_id) {
        return loanService.getLoanHistory(user_id);
    }
}
