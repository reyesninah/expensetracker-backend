package com.ibm.training.bootcamp.casestudy.expensetracker.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.casestudy.expensetracker.domain.Category;
import com.ibm.training.bootcamp.casestudy.expensetracker.service.CategoryService;
import com.ibm.training.bootcamp.casestudy.expensetracker.service.CategoryServiceImpl;

@Path("/category")
public class CategoryController {
	
	private CategoryService categoryService;
	
	public CategoryController() {
		this.categoryService = new CategoryServiceImpl();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getCategories(
			@QueryParam("categoryName") String categoryName
//			@QueryParam("categoryDate") String categoryDate){
			){
		
		try {
		
			List<Category> categories;
			
			if(StringUtils.isAllBlank(categoryName)) {
				categories = categoryService.findAllCategory();
			}else{
				categories = categoryService.findByCategory(categoryName);
			}
//			else {
//				categories = categoryService.findByMonth(categoryDate);
//			}
		
			return categories;
			
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCategory(Category category) {
			System.out.println("controller - adding record");
		try {
			categoryService.add(category);
			System.out.println("controller - adding record2");
			String result = "Category saved : "
					+ category.getCategoryId() + ""
					+ category.getCategoryName() + ""
					+ category.getCategoryBudget() + ""
					+ category.getCategoryDate();
			
			return Response.status(201).entity(result).build();
			
		}catch(Exception e) {
			System.out.println("controller - error adding record");
			throw new WebApplicationException(e);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCategoryBudget(Category category) {
		
		try {
			System.out.println("Controller - update");
			categoryService.upsert(category);
			System.out.println("Controller - update2");
			String result = "Budget updated: "
					+ category.getCategoryId() + " "
					+ category.getCategoryName() + " "
					+ category.getCategoryBudget() + " "
					+ category.getCategoryDate();
			
			return Response.status(200).entity(result).build();
			
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
}
