package uk.ac.ncl.team5project.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uk.ac.ncl.team5project.service.LoanService;

/**
 * Class: LoanExpiryTask
 * File: LoanExpiryTask.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Scheduled task that automatically checks for and processes expired loans,
 *               ensuring the loan system maintains accurate status records without manual intervention.
 *     Interface Description:
 *         - checkExpiredLoans: Periodic task that marks loans as expired when the return date has passed.
 *     Calling Sequence:
 *         - Automatically executed by Spring scheduler at configured intervals.
 *         - No manual invocation required.
 *     Argument Description:
 *         - No input parameters.
 *     List of Subordinate Classes: LoanService.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Implemented automated task for handling expired loans.
 * </pre>
 */
@Component
public class LoanExpiryTask {

    @Autowired
    private LoanService loanService;

    /**
     * Scheduled task that checks for and processes expired loans.
     * Runs daily at 1:00 AM as configured by cron expression.
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void checkExpiredLoans() {
        loanService.autoExpire();
    }
}
