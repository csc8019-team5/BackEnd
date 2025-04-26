package uk.ac.ncl.team5project.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uk.ac.ncl.team5project.service.LoanService;

@Component
public class LoanExpiryTask {

    @Autowired
    private LoanService loanService;

    /**
     * 每天凌晨1点执行过期检查
     * cron表达式：秒 分 时 日 月 星期
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void checkExpiredLoans() {
        loanService.autoExpire();
    }
}
