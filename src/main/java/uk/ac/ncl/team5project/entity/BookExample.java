package uk.ac.ncl.team5project.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * Class: BookExample
 * File: BookExample.java
 * Created on: 2025/5/8
 * Author: Menghui Yao
 *
 * Description:
 * <pre>
 *     Function: Provides dynamic query construction capabilities for Book entity database operations.
 *     Interface Description:
 *         - Calling Sequence:
 *                          setOrderByClause: Defines SQL ORDER BY clause
 *                          setDistinct: Enables distinct result filtering
 *                          createCriteria: Initiates new query criteria
 *                          or: Adds alternative OR conditions
 *                          clear: Resets all query parameters
 *         - Argument Description:
 *                          orderByClause (String): SQL sorting specification
 *                          distinct (boolean): Distinct results flag
 *                          oredCriteria (List<Criteria>): Collection of conditional expressions
 *         - List of Subordinate Classes:
 *                          GeneratedCriteria: Base class for conditional expressions
 *                          Criteria: Implementation of condition builder
 *                          Criterion: Individual query condition element
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Menghui Yao
 *     Reviewer: Menghui Yao
 *     Review Date: 2025/5/8
 *     Modification Date: 2025/5/8
 *     Modification Description: Initial implementation of book query criteria
 * </pre>
 */
public class BookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BookExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Integer value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Integer value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Integer value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Integer value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Integer value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Integer> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Integer> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Integer value1, Integer value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseIsNull() {
            addCriterion("publishing_house is null");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseIsNotNull() {
            addCriterion("publishing_house is not null");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseEqualTo(String value) {
            addCriterion("publishing_house =", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseNotEqualTo(String value) {
            addCriterion("publishing_house <>", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseGreaterThan(String value) {
            addCriterion("publishing_house >", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseGreaterThanOrEqualTo(String value) {
            addCriterion("publishing_house >=", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseLessThan(String value) {
            addCriterion("publishing_house <", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseLessThanOrEqualTo(String value) {
            addCriterion("publishing_house <=", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseLike(String value) {
            addCriterion("publishing_house like", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseNotLike(String value) {
            addCriterion("publishing_house not like", value, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseIn(List<String> values) {
            addCriterion("publishing_house in", values, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseNotIn(List<String> values) {
            addCriterion("publishing_house not in", values, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseBetween(String value1, String value2) {
            addCriterion("publishing_house between", value1, value2, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andPublishingHouseNotBetween(String value1, String value2) {
            addCriterion("publishing_house not between", value1, value2, "publishingHouse");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andBookCoverIsNull() {
            addCriterion("book_cover is null");
            return (Criteria) this;
        }

        public Criteria andBookCoverIsNotNull() {
            addCriterion("book_cover is not null");
            return (Criteria) this;
        }

        public Criteria andBookCoverEqualTo(String value) {
            addCriterion("book_cover =", value, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverNotEqualTo(String value) {
            addCriterion("book_cover <>", value, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverGreaterThan(String value) {
            addCriterion("book_cover >", value, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverGreaterThanOrEqualTo(String value) {
            addCriterion("book_cover >=", value, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverLessThan(String value) {
            addCriterion("book_cover <", value, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverLessThanOrEqualTo(String value) {
            addCriterion("book_cover <=", value, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverLike(String value) {
            addCriterion("book_cover like", value, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverNotLike(String value) {
            addCriterion("book_cover not like", value, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverIn(List<String> values) {
            addCriterion("book_cover in", values, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverNotIn(List<String> values) {
            addCriterion("book_cover not in", values, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverBetween(String value1, String value2) {
            addCriterion("book_cover between", value1, value2, "bookCover");
            return (Criteria) this;
        }

        public Criteria andBookCoverNotBetween(String value1, String value2) {
            addCriterion("book_cover not between", value1, value2, "bookCover");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNull() {
            addCriterion("available is null");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNotNull() {
            addCriterion("available is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableEqualTo(Boolean value) {
            addCriterion("available =", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotEqualTo(Boolean value) {
            addCriterion("available <>", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThan(Boolean value) {
            addCriterion("available >", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("available >=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThan(Boolean value) {
            addCriterion("available <", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThanOrEqualTo(Boolean value) {
            addCriterion("available <=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableIn(List<Boolean> values) {
            addCriterion("available in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotIn(List<Boolean> values) {
            addCriterion("available not in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableBetween(Boolean value1, Boolean value2) {
            addCriterion("available between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("available not between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureIsNull() {
            addCriterion("keyfeature is null");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureIsNotNull() {
            addCriterion("keyfeature is not null");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureEqualTo(String value) {
            addCriterion("keyfeature =", value, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureNotEqualTo(String value) {
            addCriterion("keyfeature <>", value, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureGreaterThan(String value) {
            addCriterion("keyfeature >", value, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureGreaterThanOrEqualTo(String value) {
            addCriterion("keyfeature >=", value, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureLessThan(String value) {
            addCriterion("keyfeature <", value, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureLessThanOrEqualTo(String value) {
            addCriterion("keyfeature <=", value, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureLike(String value) {
            addCriterion("keyfeature like", value, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureNotLike(String value) {
            addCriterion("keyfeature not like", value, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureIn(List<String> values) {
            addCriterion("keyfeature in", values, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureNotIn(List<String> values) {
            addCriterion("keyfeature not in", values, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureBetween(String value1, String value2) {
            addCriterion("keyfeature between", value1, value2, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andKeyfeatureNotBetween(String value1, String value2) {
            addCriterion("keyfeature not between", value1, value2, "keyfeature");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberIsNull() {
            addCriterion("available_number is null");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberIsNotNull() {
            addCriterion("available_number is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberEqualTo(Integer value) {
            addCriterion("available_number =", value, "availableNumber");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberNotEqualTo(Integer value) {
            addCriterion("available_number <>", value, "availableNumber");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberGreaterThan(Integer value) {
            addCriterion("available_number >", value, "availableNumber");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("available_number >=", value, "availableNumber");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberLessThan(Integer value) {
            addCriterion("available_number <", value, "availableNumber");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberLessThanOrEqualTo(Integer value) {
            addCriterion("available_number <=", value, "availableNumber");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberIn(List<Integer> values) {
            addCriterion("available_number in", values, "availableNumber");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberNotIn(List<Integer> values) {
            addCriterion("available_number not in", values, "availableNumber");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberBetween(Integer value1, Integer value2) {
            addCriterion("available_number between", value1, value2, "availableNumber");
            return (Criteria) this;
        }

        public Criteria andAvailableNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("available_number not between", value1, value2, "availableNumber");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}