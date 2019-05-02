package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Category;

public class CategoryDaoImpl extends DatabaseInit implements CategoryDao {

	private static CategoryDaoImpl INSTANCE;

	static public CategoryDaoImpl getInstance() {

		CategoryDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new CategoryDaoImpl();
			INSTANCE = instance;
		}

		return instance;
	}

	private CategoryDaoImpl() {
		System.out.println("going to init");
		init();
	}

//
////		date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//	}

////	private static java.sql.Date convertDate(){
////		java.util.Date utilDate = new java.util.Date();
////		return new java.sql.Date(utilDate.getDate());
////	}
//	constructor ng util date 
//	constructor ng sql date = util date .getTime()
//	java.util.Date utilDate = new java.util.Date();
//	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	
	
	
	@Override
	public void add(Category category) {
		System.out.println(category.getCategoryName()+ " inside add method1");
		String insertSql = "INSERT INTO CATEGORYTBL"
				+ "(CATEGORYNAME, CATEGORYBUDGET"
				+ ", CATEGORYDATE)"
				+ "VALUES (?, ?, ?)";

		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(insertSql)) {

			ps.setString(1, category.getCategoryName());
			ps.setBigDecimal(2, category.getCategoryBudget());
			ps.setObject(3, category.getCategoryDate().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate());
			ps.setObject(3, category.getCategoryDate());
			
			
//			System.out.println(category.getCategoryName());
//			System.out.println(category.getCategoryBudget());
//			System.out.println(category.getCategoryDate());
			ps.executeUpdate();
			System.out.println(category.getCategoryName()+ " inside add method2");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Category> findAllCategory() {

//		return findByCategory(null);
		List<Category> categories = new ArrayList<>();
		
		String sql = "SELECT categoryId, categoryName, categoryBudget,"
				+ " categoryDate FROM CATEGORYTBL";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Category category = new Category(Long.valueOf
						(results.getInt("categoryId")), 
						results.getString("categoryName"),
						results.getBigDecimal("categoryBudget"),
						results.getDate("categoryDate"));
						//.toLocalDate());
						
						//results.getDate("categoryDate"));
				categories.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return categories;
	}

	@Override
	public List<Category> findByCategory(String categoryName) {
		List<Category> categories = new ArrayList<>();

		String sql = "SELECT categoryId, categoryName, categoryBudget,"
				+ " categoryDate FROM CATEGORYTBL WHERE categoryName LIKE ? ";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(categoryName));
			
			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Category category = new Category(Long.valueOf
						(results.getInt("categoryId")), 
						results.getString("categoryName"),
						results.getBigDecimal("categoryBudget"),
//						results.getDate("categoryDate").toLocalDate());
						results.getDate("categoryDate"));
				categories.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return categories;
	}
	
//	@Override
//	public List<Category> findByMonth(Date categoryDate) {
//
//		List<Category> categories = new ArrayList<>();
//		
//		String sql = "SELECT categoryName, categoryBudget," 
//				+ " categoryDate FROM expCategoryTbl" 
//				+ " WHERE DATEPART(mm, date) = ? AND DATEPART(yy, date)= ?";
//
//		try (Connection conn = dataSource.getConnection(); 
//			PreparedStatement ps = conn.prepareStatement(sql)) {
//
//			ps.setDate(1, createSearchValue(categoryDate));
//			
//			ResultSet results = ps.executeQuery();
//			
//			while(results.next()) {
//				Category category = new Category(Long.valueOf
//						(results.getInt("categoryId")), 
//						results.getString("categoryName"),
//						results.getDouble("CategoryBudget"),
//						results.getDate("categoryDate"));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//
//
//		return categories;
//	}


	private String createSearchValue(String string) {
		String value;
		
		if (StringUtils.isBlank(string)) {
			value = "%";
		} else {
			value = string;
		}
		
		return value;
	}

	@Override
	public void update(Category category) {
		String updateSql = "UPDATE CATEGORYTBL SET categoryBudget = ?"
				+ "WHERE categoryId = ? ";
		
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(updateSql)){
			
			//ps.setString(1, category.getCategoryName());
			ps.setBigDecimal(1, category.getCategoryBudget());
			ps.setLong(2, category.getCategoryId());
			System.out.println("Controller - dao");
			ps.executeUpdate();
			
		}catch(SQLException e) {
//			System.out.println(category.getCategoryId());
//			System.out.println(category.getCategoryBudget());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Category> findByMonth(Date categoryDate) {
		return null;
	}

}
