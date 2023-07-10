package com.sb.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.blog.entity.Category;
import com.sb.blog.exception.ResourceNotFoundException;
import com.sb.blog.payloads.CategoryDto;
import com.sb.blog.repository.CategoryRepository;
import com.sb.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = this.DtoToCategory(categoryDto);
		Category save = this.categoryRepository.save(category);
		CategoryDto categoryToDto = this.CategoryToDto(save);

		return categoryToDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
		Category category = this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", id));
		category.setCategoryID(categoryDto.getCategoryID());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());

		Category save = this.categoryRepository.save(category);
		CategoryDto categoryToDto = this.CategoryToDto(save);

		return categoryToDto;
	}

	@Override
	public CategoryDto getCategoryById(int id) {

		Category category = this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", id));
		CategoryDto categoryToDto = this.CategoryToDto(category);

		return categoryToDto;
	}

	@Override
	public List<CategoryDto> getAll() {
		List<Category> categoryList = this.categoryRepository.findAll();
		List<CategoryDto> list = categoryList.stream().map(category -> this.CategoryToDto(category))
				.collect(Collectors.toList());

		return list;
	}

	@Override
	public void deleteCategory(int id) {

		Category category = this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", id));
		this.categoryRepository.delete(category);
	}

	public Category DtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}

	public CategoryDto CategoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);

		return categoryDto;
	}

}
