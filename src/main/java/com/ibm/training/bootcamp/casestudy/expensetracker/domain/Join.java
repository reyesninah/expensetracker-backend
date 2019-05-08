package com.ibm.training.bootcamp.casestudy.expensetracker.domain;

import java.math.BigDecimal;

public class Join {

	Long categoryId;
	Long expenseId;
	String expenseName;
	BigDecimal expenseAmount;
	String categoryName;
	Long categoryId_fk;
	
	
	public Join() {
		
	}
	
//	public Join (Long categoryId, Long expenseId, String expenseName, 
//			BigDecimal expenseAmount, String categoryName) {
//		this(categoryId, expenseId, expenseName, expenseAmount, categoryName);
//	}
	
	public Join(Long categoryId, Long expenseId, String expenseName,
			BigDecimal expenseAmount, String categoryName, Long categoryId_fk) {
		this.categoryId = categoryId;
		this.expenseId = expenseId;
		this.expenseName = expenseName;
		this.expenseAmount  = expenseAmount;
		this.categoryName = categoryName;
		this.categoryId_fk = categoryId_fk;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public BigDecimal getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(BigDecimal expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getCategoryId_fk() {
		return categoryId_fk;
	}

	public void setCategoryId_fk(Long categoryId_fk) {
		this.categoryId_fk = categoryId_fk;
	}
	
	
}
