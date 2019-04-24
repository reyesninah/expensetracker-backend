package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Expense;

public class ExpenseDaoImpl implements ExpenseDao{

	private static ExpenseDaoImpl INSTANCE;
	
	private JDBCDataSource dataSource;
	
	static public ExpenseDaoImpl getInstance() {
		
		ExpenseDaoImpl instance;
		if(INSTANCE != null) {
			instance= INSTANCE;
		}else {
			instance = new ExpenseDaoImpl();
			INSTANCE = instance;
		}
		return instance;
	}
	
	private ExpenseDaoImpl() {
		init();
	}
	
	private void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:EXPTRACKER");
		dataSource.setUser("username");
		dataSource.setPassword("password");
		
		createExpItemTbl();
		insertInitCategory();
	}


	private void createExpItemTbl() {
		String createSql = "CREATE TABLE expItemTbl " + 
				"(expenseId INTEGER IDENTITY PRIMARY KEY, " + 
				" expenseName VARCHAR(255), "+ 
				" expenseAmount DOUBLE," +
				" expenseDate DATE," + 
				" categoryName INTEGER, FOREIGN KEY(categoryName)"
				+ "REFERENCES expCategoryTbl(categoryName)";
		System.out.println("creating table");
		try (Connection conn = dataSource.getConnection(); 
				Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(createSql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("table created");
	}
	
	private void insertInitCategory() {
		add(new Expense("Fita Tuna Spreadz", 20.00d, new java.sql.Date(2018-10-02),"Food" ));
		add(new Expense("Choko choko", 5.00d, new java.sql.Date(2018-10-07),"Food"));
		add(new Expense("Snacku", 20.00d, new java.sql.Date(2018-10-29),"Food"));
		System.out.println("inside init()");
		
	}
	
	public void add(Expense expense) {
		
		String insertSql = "INSERT INTO expItemTbl (expenseName"
				+ "expenseAmount, expenseDate, categoryName) VALUES (?, ?, ?, ?)";
		
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(insertSql)){
			
			ps.setString(1, expense.getExpenseName());
			ps.setDouble(2, expense.getExpenseAmount());
			ps.setDate(3, (java.sql.Date) expense.getExpenseDate());
			ps.setString(4, expense.getCategoryName());
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Expense> findAllExpenses() {
//		List<Expense> expenses = new ArrayList<>();
//		
//		String sql = "SELECT expenseId, expenseName, expenseAmount"
//				+ "expenseDate, categoryId FROM expItemTbl";
//		
//		try(Connection conn = dataSource.getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql)){
//			
//			ResultSet results = ps.executeQuery();
//			
//			while(results.next()) {
//				Expense expense = new Expense(Long.valueOf(
//						results.getInt("expenseId")),
//						results.getString("expenseName"),
//						results.getDouble("expenseAmount"),
//						results.getDate("expenseDate"),
//						Long.valueOf(results.getLong("categoryId")));
//				
//				expenses.add(expense);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		return expenses;
		return findByCategory(null);
	}

	@Override
	public List<Expense> findByCategory(String categoryName) {
		List<Expense> expenses = new ArrayList<>();
		
			String sql = "SELECT expenseId, expenseName, expenseAmount,"
					+ " expenseDate, categoryId FROM expItemTbl "
					+ "WHERE categoryName = ?";
			try(Connection conn = dataSource.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)){
				
				ps.setString(1, createSearchValue(categoryName));
				
				ResultSet results = ps.executeQuery();
				
				while(results.next()) {
					Expense expense = new Expense(Long.valueOf
							(results.getInt("expenseId")),
							results.getString("expenseName"),
							results.getDouble("expenseAmount"),
							results.getDate("expenseDate"),
							results.getString("categoryName"));
					expenses.add(expense);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		
		return expenses;
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
	
	
	
	
}
