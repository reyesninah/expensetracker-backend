package com.ibm.training.bootcamp.casestudy.expensetracker.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.casestudy.expensetracker.dao.ExpenseDao;
import com.ibm.training.bootcamp.casestudy.expensetracker.dao.ExpenseDaoImpl;
import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Expense;

public class ExpenseServiceImpl implements ExpenseService {
	
	ExpenseDao expenseDao;
	
	public ExpenseServiceImpl() {
		this.expenseDao = ExpenseDaoImpl.getInstance();
	}

	@Override
	public List<Expense> findAllExpenses() {
		return expenseDao.findAllExpenses();
	}

	@Override
	public List<Expense> findByCategory(String categoryName) {
		return expenseDao.findByCategory(categoryName);
	}

	@Override
	public void add(Expense expense) {
//		if (validate(expense)) {
//			expenseDao.add(expense);
//		} else {
//			throw new IllegalArgumentException("Fields cannot be blank");
//		}
		expenseDao.add(expense);
	}

//	private boolean validate(Expense expense) {
//		return !StringUtils.isBlank(expense.getCategoryId());
//	}

}
