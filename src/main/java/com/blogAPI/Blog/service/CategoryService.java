package com.blogAPI.Blog.service;

import com.blogAPI.Blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllCategory();

    CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    void deleteCategory(Long id);

}
