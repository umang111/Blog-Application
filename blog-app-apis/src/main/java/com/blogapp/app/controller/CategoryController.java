package com.blogapp.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.app.payloads.ApiResponse;
import com.blogapp.app.payloads.CategoryDto;
import com.blogapp.app.services.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/saveCategory")
	public ResponseEntity<CategoryDto>saveUser(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto categorySaved =categoryService.saveUser(categoryDto);
		return new ResponseEntity<>(categorySaved,HttpStatus.CREATED);
	}

	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<CategoryDto> udateCategory(@Valid @PathVariable("id") Integer categoryId, @RequestBody CategoryDto categoryDto) {
		CategoryDto updatedCategory =categoryService.updateCategory(categoryId,categoryDto);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/getCategory/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer categoryId){
		CategoryDto categoryDto=categoryService.getCategoryById(categoryId);
		return ResponseEntity.ok(categoryDto);
	}
	
	@GetMapping("/getAllCategory")
	public List<CategoryDto> getAllCategory(){
		return categoryService.getAllCategory();
	}
}