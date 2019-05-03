package com.ibm.training.bootcamp.casestudy.expensetracker.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Expense;
import com.ibm.training.bootcamp.casestudy.expensetracker.service.ExpenseService;
import com.ibm.training.bootcamp.casestudy.expensetracker.service.ExpenseServiceImpl;

@Path("/expense")
public class ExpenseController {

	private ExpenseService expenseService;
	
	public ExpenseController() {
		this.expenseService = new ExpenseServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expense> getExpenses(
			@QueryParam("categoryId") String categoryName){
		System.out.println("expcontroller");
		try {
			List<Expense> expenses;
			
//			if(StringUtils.isBlank(categoryName)) {
//				expenses = expenseService.findAllExpenses();
//			}else {
//				expenses = expenseService.findByCategory(categoryName);
//			}
			expenses = expenseService.findAllExpenses();
			
			return expenses; 
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
			
		}
		 
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addExpense(Expense expense) {
		System.out.println("controller - adding expense");
		try {
			expenseService.add(expense);
			System.out.println("controller - adding expense2");
			String result = "Expense added : "
					+ expense.getExpenseId() + ""
					+ expense.getExpenseName() + ""
					+ expense.getExpenseAmount() + ""
					+ expense.getExpenseDate() + ""
					+ expense.getCategoryId();
			 return Response.status(200).entity(result).build();
			
		}catch(Exception e) {
			throw new WebApplicationException(e);
			
		}
	}
}
