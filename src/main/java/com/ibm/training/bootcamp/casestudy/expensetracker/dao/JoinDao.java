package com.ibm.training.bootcamp.casestudy.expensetracker.dao;

import java.util.List;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Join;

public interface JoinDao {

	List<Join> getReportByCategory(Long categoryId);

}
