package com.sb.blog.service;

import java.util.List;

import com.sb.blog.payloads.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto, int id);

	public CategoryDto getCategoryById(int id);

	public List<CategoryDto> getAll();

	public void deleteCategory(int id);

}
