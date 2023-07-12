package com.blogAPI.Blog.service.Impl;

import com.blogAPI.Blog.entity.Category;
import com.blogAPI.Blog.exception.ResourceNotFoundException;
import com.blogAPI.Blog.payload.CategoryDto;
import com.blogAPI.Blog.repository.CategoryRepository;
import com.blogAPI.Blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category category1 = categoryRepository.save(category);
        return modelMapper.map(category1, CategoryDto.class);

    }

    @Override
    public CategoryDto getCategory(Long id) {
       Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","Id", id));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map((e)-> modelMapper.map(e, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","Id", id));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Category category1 = categoryRepository.save(category);

        return modelMapper.map(category1, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","Id", id));
        categoryRepository.delete(category);
    }
}
