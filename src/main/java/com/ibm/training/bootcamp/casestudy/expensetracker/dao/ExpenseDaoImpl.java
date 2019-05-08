package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Expense;

public class ExpenseDaoImpl extends DatabaseInit implements ExpenseDao{

	private static ExpenseDaoImpl INSTANCE;
	
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
	
	private ExpenseDaoImpl(){
		System.out.println("going to init");
		init();
	}
	
	public void add(Expense expense) {
		String insertSql = "INSERT INTO EXPENSETBL (expenseName,"
				+ "expenseAmount, expenseDate"
				+ ", categoryId_FK)"
				+ " VALUES (?, ?, ?, ?)";
		
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(insertSql)){
			
			System.out.println("trying to add expense");
			
			ps.setString(1, expense.getExpenseName());
			ps.setBigDecimal(2, expense.getExpenseAmount());
			ps.setObject(3, expense.getExpenseDate().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate());
			ps.setLong(4, Long.valueOf(expense.getCategoryId()));
			
			System.out.println(expense.getExpenseName() + " " +
					 expense.getExpenseAmount() + " " +
					 expense.getExpenseDate().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate() + " " +
					 expense.getCategoryId());
			ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("add dao - error adding");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Expense> findAllExpenses() {
		List<Expense> expenses = new ArrayList<>();
		
//		String sql = "SELECT expenseId, expenseName, expenseAmount,"
//				+ "expenseDate, categoryId_FK FROM expensetbl";
		
		String sql = "SELECT * FROM expensetbl";
		
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			ResultSet results = ps.executeQuery();
			while(results.next()) {
				Expense expense = new Expense(Long.valueOf(
						results.getInt("expenseId")),
						results.getString("expenseName"),
						results.getBigDecimal("expenseAmount"),
						results.getDate("expenseDate"),
						Long.valueOf(results.getLong("categoryId_FK")));
				expenses.add(expense);
				System.out.println("results : " + expense.getExpenseId());
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return expenses;

	}
	
	
}
