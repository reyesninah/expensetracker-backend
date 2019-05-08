package com.ibm.training.bootcamp.casestudy.expensetracker.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Category {
	
	Long categoryId;
	private String categoryName;
	//private BigDecimal categoryBudget = new BigDecimal(1000);
	private BigDecimal categoryBudget;
	//private LocalDate categoryDate = LocalDate.now();
	//private java.sql.Date categoryDate;
	private java.util.Date categoryDate;
	
	public Category() {
		
	}
	
//	public Category( String categoryName,
//			BigDecimal categoryBudget, LocalDate categoryDate) {
//		this(null, categoryName, categoryBudget, categoryDate);
//	}
//
//	
//	public Category(Long categoryId, String categoryName,
//			BigDecimal categoryBudget, LocalDate categoryDate) {
//		this.categoryId = categoryId;
//		this.categoryName = categoryName;
//		this.categoryBudget = categoryBudget;
//		this.categoryDate = categoryDate;
//	}
	
	public Category( String categoryName,
			BigDecimal categoryBudget, java.sql.Date categoryDate) {
		this(null, categoryName, categoryBudget, categoryDate);
	}

	
	public Category(Long categoryId, String categoryName,
			BigDecimal categoryBudget, java.sql.Date categoryDate) {
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

	public BigDecimal getCategoryBudget() {
		return categoryBudget;
	}

	public void setCategoryBudget(BigDecimal categoryBudget) {
		this.categoryBudget = categoryBudget;
	}

//	public LocalDate getCategoryDate() {
//		return categoryDate;
//	}
//
//	public void setCategoryDate(LocalDate categoryDate) {
//		this.categoryDate = categoryDate;
//	}
	
//	public java.sql.Date getCategoryDate() {
//		return categoryDate;
//	}
//
//	public void setCategoryDate(java.sql.Date categoryDate) {
//		this.categoryDate = categoryDate;
//	}
	
	public java.util.Date getCategoryDate() {
		return categoryDate;
	}

	public void setCategoryDate(java.util.Date categoryDate) {
		this.categoryDate = categoryDate;
	}

}
