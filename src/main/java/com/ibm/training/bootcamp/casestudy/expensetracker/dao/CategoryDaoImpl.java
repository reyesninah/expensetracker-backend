package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

////		date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//	constructor ng util date 
//	constructor ng sql date = util date .getTime()
//	java.util.Date utilDate = new java.util.Date();
//	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	@Override
	public void add(Category category) {
		System.out.println(category.getCategoryName() + " inside add method1");
		String insertSql = "INSERT INTO CATEGORYTBL" 
		+ "(CATEGORYNAME, CATEGORYBUDGET" + ", CATEGORYDATE)"
				+ "VALUES (?, ?, ?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {

			ps.setString(1, category.getCategoryName());
			ps.setBigDecimal(2, category.getCategoryBudget());
			ps.setObject(3, category.getCategoryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			// ps.setObject(3, category.getCategoryDate());
			ps.executeUpdate();

			System.out.println(category.getCategoryName() + " inside add method2");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Category> findAllCategory() {

//		return findByCategory(null);
		List<Category> categories = new ArrayList<>();

		String sql = "SELECT categoryId, categoryName, categoryBudget," + " categoryDate FROM CATEGORYTBL";

		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Category category = new Category(Long.valueOf(results.getInt("categoryId")),
						results.getString("categoryName"), results.getBigDecimal("categoryBudget"),
						results.getDate("categoryDate"));
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

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(categoryName));

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Category category = new Category(Long.valueOf(results.getInt("categoryId")),
						results.getString("categoryName"), results.getBigDecimal("categoryBudget"),
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
		String updateSql = "UPDATE CATEGORYTBL SET categoryBudget = ?" + "WHERE categoryId = ? ";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			// ps.setString(1, category.getCategoryName());
			ps.setBigDecimal(1, category.getCategoryBudget());
			ps.setLong(2, category.getCategoryId());
			System.out.println("Controller - dao");
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Update catch- categoryId:" + category.getCategoryId());
			System.out.println("Update catch- categoryBudget:" + category.getCategoryBudget());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Category> findByMonthYear(String categoryYear, String categoryMonth, Date categoryDate) {

		List<Category> categories = new ArrayList<>();
//		String testDate = "29-Apr-2010,13:00:14 PM";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = formatter.parse(testDate);
//		System.out.println(date);

//		String startDate="01-02-2013";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date date = sdf1.parse(startDate);
//		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());  

		categoryYear = categoryYear.substring(0, 3);

		categoryMonth = categoryMonth.substring(5, 6);
		String categoryDateStr = categoryYear + "-" + categoryMonth + "-" + "02";
		System.out.println(categoryYear);
		System.out.println(categoryMonth);
		System.out.println(categoryDateStr);

		// LocalDate categoryDateUtil = formatter.parse(categoryDateStr);
		java.util.Date categoryDateUtil;
		try {
			categoryDateUtil = sdf1.parse(categoryDateStr);
			categoryDate = new java.sql.Date(categoryDateUtil.getTime());
		} catch (ParseException e1) {

			e1.printStackTrace();
		}

		// categoryDate = categoryDateTemp;
		// categoryDate.valueOf(categoryDateLoc);
		String sql = "SELECT * FROM CATEGORYTBL WHERE MONTH(categoryDate) = ?" + "AND YEAR(categoryDate)= ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setDate(1, categoryDate);
//			ps.setString(1, searchYear(categoryYear)); 
//			ps.setString(2, searchMonth(categoryMonth));
			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Category category = new Category(Long.valueOf(results.getInt("categoryId")),
						results.getString("categoryName"), results.getBigDecimal("categoryBudget"),
						results.getDate("categoryDate"));
				categories.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return categories;
	}

	private String searchYear(String string) {
		String value;

		if (StringUtils.isBlank(string)) {
			value = "%";
		} else {
			value = string;
		}

		return value;
	}

	private String searchMonth(String string) {
		String value;

		if (StringUtils.isBlank(string)) {
			value = "%";
		} else {
			value = string;
		}

		return value;
	}

	@Override
	public List<Category> findByMonth(Date categoryDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findByMonthYear(Date categoryDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long categoryId) {
		String updateSql = "UPDATE CATEGORYTBL SET categoryBudget = ?"
				+ "WHERE categoryId = ?";
		Category category = new Category();
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(updateSql)) {
			System.out.println("Dao - start");
			
			
			System.out.println("Budget: " + category.getCategoryBudget());
			System.out.println("Id: " + category.getCategoryId());
			ps.setBigDecimal(1, category.getCategoryBudget());
			ps.setLong(2, category.getCategoryId());
			
			
			ps.executeUpdate();
			
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
