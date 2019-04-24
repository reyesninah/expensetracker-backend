package com.ibm.training.bootcamp.casestudy.expensetracker.domain;

import java.util.Date;

public class Expense {
	
	Long expenseId;
	private String expenseName;
	private double expenseAmount;
	private Date expenseDate;
	String categoryName;
	
	public Expense() {
		
	}
	
	public Expense(String expenseName,double expenseAmount,
			Date expenseDate,String categoryName) {
		this(null, expenseName, expenseAmount, expenseDate,
				categoryName);
	}
	
	public Expense(Long expenseId, String expenseName, 
			double expenseAmount, Date expenseDate,
			String categoryName) {
		this.expenseName = expenseName;
		this.expenseAmount = expenseAmount;
		this.expenseDate = expenseDate;
		this.categoryName = categoryName;
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

	public double getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
}
