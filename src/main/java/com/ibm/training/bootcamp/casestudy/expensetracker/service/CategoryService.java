package com.ibm.training.bootcamp.casestudy.expensetracker.service;

import java.sql.Date;
import java.util.List;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Category;

public interface CategoryService {

	public void add(Category category);

	public List<Category> findAllCategory();

	public void upsert(Category category);

	public List<Category> findByMonth(Date categoryDate);

	public List<Category> findByCategory(String categoryName);

	public List<Category> findByMonthYear(java.util.Date categoryDate);

}
