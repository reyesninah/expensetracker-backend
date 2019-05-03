package com.ibm.training.bootcamp.casestudy.expensetracker.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Expense {
	
	Long expenseId;
	private String expenseName;
	private BigDecimal expenseAmount = new BigDecimal(1000);
	private java.util.Date expenseDate;
	Long categoryId;
	
	public Expense() {
		
	}
	
	public Expense(String expenseName,BigDecimal expenseAmount,
			Date expenseDate,Long categoryId) {
		this(null, expenseName, expenseAmount, expenseDate,
				categoryId);
	}
	
	public Expense(Long expenseId, String expenseName, 
			BigDecimal expenseAmount, Date expenseDate,
			Long categoryId) {
		this.expenseName = expenseName;
		this.expenseAmount = expenseAmount;
		this.expenseDate = expenseDate;
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

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

//	public String getCategoryName() {
//		return categoryName;
//	}
//
//	public void setCategoryName(String categoryName) {
//		this.categoryName = categoryName;
//	}
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryName(Long categoryId) {
		this.categoryId = categoryId;
	}

	
	
}
