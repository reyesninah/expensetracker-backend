package com.ibm.training.bootcamp.casestudy.expensetracker.domain;

import java.time.LocalDate;
import java.util.Date;

public class Category {
	
	Long categoryId;
	private String categoryName;
	private double categoryBudget = 1000;
	//private LocalDate categoryDate = LocalDate.now();
	private Date categoryDate;
	
	public Category() {
		
	}
	
	public Category( String categoryName,
			double categoryBudget, Date categoryDate) {
		this(null, categoryName, categoryBudget, categoryDate);
	}

	
	public Category(Long categoryId, String categoryName,
			double categoryBudget, Date categoryDate) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryBudget = categoryBudget;
		this.categoryDate = categoryDate;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public double getCategoryBudget() {
		return categoryBudget;
	}

	public void setCategoryBudget(double categoryBudget) {
		this.categoryBudget = categoryBudget;
	}

	public Date getCategoryDate() {
		return categoryDate;
	}

	public void setCategoryDate(Date categoryDate) {
		this.categoryDate = categoryDate;
	}

}
