package com.ibm.training.bootcamp.casestudy.expensetracker.domain;

import java.time.LocalDate;
import java.util.Date;

public class Category {
	
	Long categoryId;
	private String categoryName;
	private double categoryBudget = 1000;
	private LocalDate categoryDate = LocalDate.now();
	
	public Category() {
		
	}
	
	public Category( String categoryName,
			double categoryBudget, LocalDate categoryDate) {
		this(null, categoryName, categoryBudget, categoryDate);
	}

	
	public Category(Long categoryId, String categoryName,
			double categoryBudget, LocalDate categoryDate) {
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

	public LocalDate getCategoryDate() {
		return categoryDate;
	}

	public void setCategoryDate(LocalDate categoryDate) {
		this.categoryDate = categoryDate;
	}

}
