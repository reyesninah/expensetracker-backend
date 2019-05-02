package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import org.hsqldb.jdbc.JDBCDataSource;

public abstract class DatabaseInit {
	
	public JDBCDataSource dataSource;
	public void init() {
		System.out.println("inside init");
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:hsql://localhost/exptrackerdb");
		dataSource.setUser("SA");
		dataSource.setPassword("");

	}
	
}
