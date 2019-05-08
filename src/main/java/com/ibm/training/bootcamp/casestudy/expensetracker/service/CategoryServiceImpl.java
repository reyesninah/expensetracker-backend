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
		System.out.println("inside add");
		System.out.println(category.getCategoryName());
			categoryDao.add(category);
		//}
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
	public void update(Category category) {
		System.out.println(category.getCategoryId());
			if(category.getCategoryId() != null 
					&& category.getCategoryId()>=0){
				System.out.println("Service - updateupdate");
				categoryDao.update(category);
			}else {
				System.out.println("Service - updateadd");
				//categoryDao.add(category);
				System.out.println("error upsert ");
			}
		}

	
//	private boolean validate(Category category) {
////		if(StringUtils.isAnyBlank(category.getCategoryName()) && 
////				category.getCategoryBudget().isEmpty()) &&
////				)
//		System.out.println("inside validate");
//		
//		return !StringUtils.isAnyBlank(category.getCategoryName());
//	}


	@Override
	public List<Category> findByCategory(String categoryName) {
		return categoryDao.findByCategory(categoryName);
	}

//	@Override
//	public List<Category> findByMonthYear(java.util.Date categoryDate) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Category> findByMonthYear(String categoryYear, String categoryMonth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findByMonthYear(String categoryYear, String categoryMonth,java.util.Date categoryDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upsert(Category category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long categoryId) {
		categoryDao.update(categoryId);
		
	}

}
