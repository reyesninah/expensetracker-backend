package com.ibm.training.bootcamp.casestudy.expensetracker.service;

import java.util.List;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Expense;

public interface ExpenseService {

	List<Expense> findAllExpenses();

	void add(Expense expense);

	List<Expense> findByCategory(String categoryName);

}
