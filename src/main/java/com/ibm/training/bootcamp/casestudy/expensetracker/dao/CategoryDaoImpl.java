package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Category;

public class CategoryDaoImpl implements CategoryDao {

	private static CategoryDaoImpl INSTANCE;

	private JDBCDataSource dataSource;

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
		init();
	}

	private void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:EXPTRACKER");
		dataSource.setUser("username");
		dataSource.setPassword("password");

		createExpCategoryTbl();
		insertInitCategory();
	}

	private void createExpCategoryTbl() {
		String createSql = "CREATE TABLE expCategoryTbl "
				+ "(categoryId INTEGER IDENTITY PRIMARY KEY,"
				+ "categoryName VARCHAR(255),"
				+ "categoryBudget DOUBLE,"
				+ "categoryDate DATE)";

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(createSql);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitCategory() {
		add(new Category("Food", 5000, new Date(2019-04-12)));
		add(new Category("Others", 900, new java.sql.Date(2019-04-13)));
		add(new Category("Utils", 9000, new java.sql.Date(2019-04-14)));
		add(new Category("Fare", 3500, new java.sql.Date(2019-04-15)));
	}

	@Override
	public void add(Category category) {

		String insertSql = "INSERT INTO expCategoryTbl"
				+ "(categoryName, categoryBudget, categoryDate)"
				+ "VALUES (?, ?, ?)";

		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(insertSql)) {

			ps.setString(1, category.getCategoryName());
			ps.setDouble(2, category.getCategoryBudget());
			ps.setDate(3, (Date) category.getCategoryDate());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Category> findAllCategory() {

		return findByCategory(null);
	}

	@Override
	public List<Category> findByCategory(String categoryName) {
		List<Category> categories = new ArrayList<>();

		String sql = "SELECT categoryId, categoryName, categoryBudget, categoryDate"
				+ " FROM expCategoryTbl WHERE categoryName LIKE ? ";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(categoryName));
			
			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Category category = new Category(Long.valueOf
						(results.getInt("categoryId")), 
						results.getString("categoryName"),
						results.getDouble("categoryBudget"),
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
		String updateSql = "UPDATE expCategoryTbl SET categoryName = ?,"
				+ "categoryBudget = ?"
				+ "WHERE categoryId = ? ";
		
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(updateSql)){
			
			ps.setString(1, category.getCategoryName());
			ps.setDouble(1, category.getCategoryBudget());
			ps.setLong(2, category.getCategoryId());
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
