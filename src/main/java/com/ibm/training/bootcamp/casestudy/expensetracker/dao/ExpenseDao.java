package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import java.util.List;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Expense;

public interface ExpenseDao {
	
	public void add(Expense expense);

	public List<Expense> findAllExpenses();

	public List<Expense> findByCategory(String categoryName);

}
