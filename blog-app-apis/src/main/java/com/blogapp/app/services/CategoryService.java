package com.blogapp.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.app.entitys.Category;
import com.blogapp.app.payloads.CategoryDto;
import com.blogapp.app.repositories.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryrepo;

	//create category
	public CategoryDto saveUser(CategoryDto ategoryDto) {
		
		Category category =modelMapper.map(ategoryDto, Category.class);
		categoryrepo.save(category);
		CategoryDto savedCategory =modelMapper.map(category, CategoryDto.class);
		return savedCategory;
	}

	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		
		Category getCategory = categoryrepo.findById(categoryId).get();
		getCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		getCategory.setCategoryDescription(categoryDto.getCategoryDescription());
	    Category updatedCategory =categoryrepo.save(getCategory);
	    CategoryDto updatedCate = modelMapper.map(updatedCategory,CategoryDto.class);
		return updatedCate;
	}

	public void deleteCategory(Integer categoryId) {
		Category getCategory = categoryrepo.findById(categoryId).get();
		categoryrepo.delete(getCategory);
	}

	public CategoryDto getCategoryById(Integer categoryId) {
		Category getCategory = categoryrepo.findById(categoryId).get();
		 CategoryDto updatedCate = modelMapper.map(getCategory,CategoryDto.class);
		return updatedCate;
	}

	public List<CategoryDto> getAllCategory() {
		List<Category> getAllCategory =categoryrepo.findAll();
		List<CategoryDto> collect = getAllCategory.stream()
				.map(category->categoryToCategoryDto(category)).collect(Collectors.toList());
		return collect;
	}
	
	public CategoryDto categoryToCategoryDto(Category categoryDto) {
		CategoryDto categoryDtos =modelMapper.map(categoryDto,CategoryDto.class);
		return categoryDtos;
	}
}
