package uk.ac.ncl.team5project.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class: AdminExample
 * File: AdminExample.java
 * Created on: 2025/4/25
 * Author: menghui yao
 *
 * Description:
 * <pre>
 *     Function: Provides criteria-based filtering capabilities for Admin entities in the database.
 *     Interface Description:
 *         - Calling Sequence:
 *                          setOrderByClause: Sets the ordering clause for the SQL query.
 *                          setDistinct: Sets whether to filter for distinct results.
 *                          createCriteria: Creates a new criteria for query filtering.
 *                          clear: Clears all criteria and settings.
 *         - Argument Description:
 *                          orderByClause (String): SQL order clause to sort results.
 *                          distinct (boolean): Whether to ensure unique results.
 *                          oredCriteria (List<Criteria>): List of criteria to filter results.
 *         - List of Subordinate Classes:
 *                          Criteria: Internal class representing a single set of query conditions.
 *                          Criterion: Internal class representing an individual condition.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: menghui yao
 *     Reviewer: menghui yao
 *     Review Date: 2025/4/25
 *     Modification Date: 2025/4/25
 *     Modification Description: Added comprehensive JavaDoc comments.
 * </pre>
 */
public class AdminExample {
    /** SQL ORDER BY clause for sorting results */
    protected String orderByClause;

    /** Whether the results should be distinct */
    protected boolean distinct;

    /** List of Criteria objects representing query conditions */
    protected List<Criteria> oredCriteria;

    /**
     * Constructor for AdminExample.
     * Initializes the oredCriteria list for storing filtering conditions.
     */
    public AdminExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * Sets the ORDER BY clause for the SQL query.
     * @param orderByClause The SQL ORDER BY clause for sorting results.
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * Retrieves the current ORDER BY clause for the SQL query.
     * @return The SQL ORDER BY clause for sorting results.
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * Sets whether to filter for distinct results.
     * @param distinct true to filter for distinct results, false otherwise.
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * Checks whether the distinct filter is enabled.
     * @return true if distinct filtering is enabled, false otherwise.
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * Retrieves the list of Criteria objects representing the query conditions.
     * @return List of Criteria objects.
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * Adds a new Criteria object to the oredCriteria list.
     * @param criteria The Criteria object to add.
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * Creates and adds a new empty Criteria object to the oredCriteria list.
     * @return The newly created Criteria object.
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * Creates a new Criteria object if none exists, then returns it.
     * @return A Criteria object for adding query conditions.
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * Internally creates a new empty Criteria object.
     * @return A new Criteria object.
     */
    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    /**
     * Clears all criteria and settings, resetting the example object to its initial state.
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * Internal class representing a single set of query conditions.
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * Default constructor for Criteria.
         */
        protected Criteria() {
            super();
        }
    }

    /**
     * Internal class representing an individual condition within a Criteria object.
     */
    public static class Criterion {
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;
        private String typeHandler;

        /**
         * Constructor for Criterion with only a condition (e.g., IS NULL).
         * @param condition The SQL condition as a string.
         */
        protected Criterion(String condition) {
            this.condition = condition;
            this.noValue = true;
        }

        /**
         * Constructor for Criterion with a single value (e.g., = or <).
         * @param condition The SQL condition as a string.
         * @param value The value to compare against.
         * @param typeHandler The handler for managing SQL type conversions.
         */
        protected Criterion(String condition, Object value, String typeHandler) {
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            this.singleValue = !(value instanceof List<?>);
            this.listValue = value instanceof List<?>;
        }

        /**
         * Constructor for Criterion with two values (e.g., BETWEEN).
         * @param condition The SQL condition as a string.
         * @param value The first value to compare against.
         * @param secondValue The second value to compare against.
         */
        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}