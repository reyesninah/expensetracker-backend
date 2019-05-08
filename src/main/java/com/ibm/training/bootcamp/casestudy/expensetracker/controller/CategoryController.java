package com.ibm.training.bootcamp.casestudy.expensetracker.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
			@QueryParam("categoryName") String categoryName,
			@QueryParam("categoryDate") Date categoryDate){
		
		try {
		
			List<Category> categories;
			
			if(StringUtils.isAllBlank(categoryName)) {
				categories = categoryService.findAllCategory();
			}else
				//(StringUtils.isNotBlank(categoryName)){
				categories = categoryService.findByCategory(categoryName);
			//}
//			else {
//				categories = categoryService.findByMonthYear(categoryName, categoryName, categoryDate);
//			}
//		
			return categories;
			
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
		
	}
	
//	@GET
//	@Path("categoryDate")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Category getCategoryByMonthYear(
//			@PathParam("categoryDate") Date categoryDate) {
//
//		try {
//			java.util.Date givMonthYear = categoryDate;
//			Category category = categorySevice.find(givMonthYear);
//			return category;
//		} catch (Exception e) {
//			throw new WebApplicationException(e);
//		}
//	}

	
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
	@Path("{categoryId}") 
	public Response updateCategoryBudget(
			@PathParam("categoryId") Long categoryId,
			Category category) {
		System.out.println("Controller update - start");
		try {			
			category.setCategoryId(categoryId);
			categoryService.update(category); 
			String result = "Budget updatedxxxxx";
			return Response.status(200).entity(result).build();
			
		}catch(Exception e) {
			System.out.println("Error: controller update2");
			throw new WebApplicationException(e);
		}
	}
}
