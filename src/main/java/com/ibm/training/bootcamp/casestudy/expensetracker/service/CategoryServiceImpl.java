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
//		if(validate(category) == false) {
//			System.out.println("try");
//			categoryDao.add(category);
//			
//		} else {
//			System.out.println("ayaw add");
//			throw new IllegalArgumentException 
//			("Field CategoryName cannot be blank");
//					
//		}
//		try {
//			if(categoryName)
//			categoryDao.add(category);
//			
//		}catch(Exception e) {
//			throw new IllegalArgumentException ("Field CategoryName cannot be blank");
//		}
//		if(category.getCategoryId() != null && category.getCategoryId() >= 0) {
//			categoryDao.update(category);
//		} else {
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
	public void upsert(Category category) {
//		if(validate(category)) {
//			if(category.getCategoryName() != null )
////					&& category.getCategoryName()>=0) 
//			{
//				categoryDao.update(category);
//				
//			}else {
//				categoryDao.add(category);
//			}
//		}else {
//			throw new IllegalArgumentException("Fields cannot be blank");
//		}
		
		categoryDao.add(category);
		
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
