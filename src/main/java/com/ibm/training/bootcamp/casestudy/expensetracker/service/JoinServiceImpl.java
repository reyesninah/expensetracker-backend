package com.ibm.training.bootcamp.casestudy.expensetracker.service;

import java.util.List;

import com.ibm.training.bootcamp.casestudy.expensetracker.dao.JoinDao;
import com.ibm.training.bootcamp.casestudy.expensetracker.dao.JoinDaoImpl;
import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Join;

public class JoinServiceImpl implements JoinService {

	JoinDao joinDao;
	
	public JoinServiceImpl() {
		this.joinDao = JoinDaoImpl.getInstance();
	}
	
	@Override
	public List<Join> getReportByCategory(Long categoryId) {
		return joinDao.getReportByCategory(categoryId);
	}

}
