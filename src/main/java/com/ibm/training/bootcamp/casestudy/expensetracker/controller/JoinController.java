package com.ibm.training.bootcamp.casestudy.expensetracker.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Join;
import com.ibm.training.bootcamp.casestudy.expensetracker.service.JoinService;
import com.ibm.training.bootcamp.casestudy.expensetracker.service.JoinServiceImpl;

@Path("/report")
public class JoinController {

	private JoinService joinService;
	
	public JoinController() {
		this.joinService = new JoinServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Join> getReports(
			@QueryParam("categoryId") Long categoryId){
		
		try {
			List<Join> joinReport;
			System.out.println("Join - inside controller");
			joinReport = joinService.getReportByCategory(categoryId);
			
			return joinReport;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
		
	}
	
}
