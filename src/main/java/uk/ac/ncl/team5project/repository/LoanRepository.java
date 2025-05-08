package uk.ac.ncl.team5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uk.ac.ncl.team5project.entity.jpa.Loan;

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
 *     Modification Date: 27/04/2025
 *     Modification Description: Implemented with custom loan status filters and queries.
 * </pre>
 */

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    /**
     * Retrieves active loans for a specific user that have not been returned yet.
     * Active loans are defined as records where the return date is null.
     *
     * @param userId The ID of the user to get active loans for
     * @return List of Loan objects with null return dates
     */
    List<Loan> findByUserIdAndReturnDateIsNull(Integer userId);

    /**
     * Retrieves all loan records for a specific user, including both active and returned loans.
     * This provides a complete history of a user's borrowing activity.
     *
     * @param userId The ID of the user to get loan history for
     * @return Complete list of all loan records for the user
     */
    List<Loan> findByUserId(Integer userId);

    /**
     * Retrieves all active loans across the entire system.
     * Used primarily by scheduled tasks for system-wide operations such as expiry checks.
     *
     * @return List of all active loans with null return dates
     */
    List<Loan> findByReturnDateIsNull();

    /**
     * Retrieves all loans that have been returned across the system.
     * These are identified as loans where the return date is not null.
     *
     * @return List of all returned loan records
     */
    List<Loan> findByReturnDateIsNotNull();

    /**
     * Retrieves loans that have passed their due date but have not been returned.
     * Identifies overdue loans by comparing estimated return date with current date.
     *
     * @return List of expired loan records
     */
    @Query("SELECT l FROM Loan l WHERE l.returnDate IS NULL AND l.returnDateEstimated < CURRENT_DATE")
    List<Loan> findExpiredLoans();

    /**
     * Retrieves a specific loan record by its exact ID.
     * Uses native SQL to directly query the USER_BOOK table.
     *
     * @param loanId The ID of the loan record to retrieve
     * @return The Loan object matching the ID or null if not found
     */
    @Query(value = "SELECT * FROM `USER_BOOK` WHERE id = :loanId", nativeQuery = true)
    Loan findLoanById(@Param("loanId") Integer loanId);
}
