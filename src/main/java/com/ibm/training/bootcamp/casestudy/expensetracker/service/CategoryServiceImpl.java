package com.ibm.training.bootcamp.casestudy.expensetracker.service;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.casestudy.expensetracker.dao.CategoryDao;
import com.ibm.training.bootcamp.casestudy.expensetracker.dao.CategoryDaoImpl;
import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Category;

public class CategoryServiceImpl implements CategoryService {

	CategoryDao categoryDao;
	
	public CategoryServiceImpl() {
		this.categoryDao = CategoryDaoImpl.getInstance();
	}

	@Override
	public void add(Category category) {
		if(validate(category)) {
			categoryDao.add(category);
		} else {
			throw new IllegalArgumentException 
			("Field CategoryName cannot be blank");
		}
	}
	
	@Override
	public List<Category> findAllCategory() {
		return categoryDao.findAllCategory();
	}
	
	@Override
	public List<Category> findByMonth(Date categoryDate) {
		return categoryDao.findByMonth(categoryDate);
	}

	@Override
	public void upsert(Category category) {
		if(validate(category)) {
			if(category.getCategoryId() != null && 
					category.getCategoryId()>=0) {
				categoryDao.update(category);
				
			}else {
				categoryDao.add(category);
			}
		}else {
			throw new IllegalArgumentException("Fields cannot be blank");
		}
		
	}
	
	private boolean validate(Category category) {
//		if(StringUtils.isAnyBlank(category.getCategoryName()) && 
//				category.getCategoryBudget().isEmpty()) &&
//				)
		
		return !StringUtils.isAnyBlank(category.getCategoryName());
	}

	@Override
	public List<Category> findByMonth(String categoryDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findByCategory(String categoryName) {
		return categoryDao.findByCategory(categoryName);
	}
}
