package uk.ac.ncl.team5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.ac.ncl.team5project.entity.Loan;

import java.util.List;
/**
 * Class: LoanRepository
 * File: LoanRepository.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Provides custom data access methods for loan records, supporting operations
 *               such as finding active, expired, and returned loans, as well as user-specific queries.
 *     Interface Description:
 *         - findByUserIdAndReturnDateIsNull: Retrieves active loans for a user.
 *         - findByUserId: Retrieves all loan records for a user.
 *         - findByReturnDateIsNull: Retrieves globally active loans.
 *         - findByReturnDateIsNotNull: Retrieves returned loan records.
 *         - findExpiredLoans: Custom query to retrieve overdue unreturned loans.
 *     Calling Sequence:
 *         - Used by LoanService and LoanExpiryTask.
 *     Argument Description:
 *         - userId: ID of the user associated with the loan.
 *     List of Subordinate Classes: Loan.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Implemented with custom loan status filters and queries.
 * </pre>
 */

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    // Find active loans for a user (not yet returned)
    List<Loan> findByUserIdAndReturnDateIsNull(Integer userId);

    // Find all loan records for a user (including returned ones)
    List<Loan> findByUserId(Integer userId);

    // Find all globally active loans (used by scheduled tasks)
    List<Loan> findByReturnDateIsNull();


    // Find all returned loan records
    List<Loan> findByReturnDateIsNotNull();

    // Find expired loans that have not been returned
    @Query("SELECT l FROM Loan l WHERE l.returnDate IS NULL AND l.returnDateEstimated < CURRENT_DATE")
    List<Loan> findExpiredLoans();
}
