package com.ibm.training.bootcamp.casestudy.expensetracker.service;

import java.util.List;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Join;

public interface JoinService {

	List<Join> getReportByCategory(Long categoryId);

}
