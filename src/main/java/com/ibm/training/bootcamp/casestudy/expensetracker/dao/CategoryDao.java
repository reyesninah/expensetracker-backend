package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import java.sql.Date;
import java.util.List;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Category;

public interface CategoryDao {

	public void add(Category category);

	public List<Category> findAllCategory();

	public void update(Category category);

	public List<Category> findByMonth(Date categoryDate);

	public List<Category> findByCategory(String categoryName);

	List<Category> findByMonthYear(Date categoryDate);

	List<Category> findByMonthYear(String categoryYear, String categoryMonth, Date categoryDate);

	public void update(Long categoryId);

}
