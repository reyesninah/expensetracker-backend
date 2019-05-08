package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Join;

public class JoinDaoImpl extends DatabaseInit implements JoinDao{
	
	private static JoinDaoImpl INSTANCE;
	
	static public JoinDaoImpl getInstance() {
		JoinDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new JoinDaoImpl();
			INSTANCE = instance;
		}
		return instance;
	}

	private JoinDaoImpl() {
		System.out.println("Join going init");
		init();
	}

	@Override
	public List<Join> getReportByCategory(Long categoryId) {
		
		List<Join> joinReport = new ArrayList<>();
		
		String sql = "SELECT expenseId, expenseName, categoryId_fk, categoryId, categoryName "
				+ "FROM EXPENSETBL JOIN CATEGORYTBL on EXPENSETBL.categoryid_fk = categorytbl.categoryid "
				+ "WHERE expensetbl.CATEGORYiD_FK =?";
		
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			
			ResultSet results= ps.executeQuery();
			System.out.println("results : " + results );
			
			while(results.next()) {
				Join join = new Join(
						Long.valueOf(results.getInt("categoryId")),
						Long.valueOf(results.getInt("expenseId")),
						results.getString("expenseName"),
						results.getBigDecimal("expenseAmount"),
						results.getString("categoryName"),
						Long.valueOf(results.getInt("categoryid_fk")));
						
				joinReport.add(join);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return joinReport;
	}	
	
	
}
