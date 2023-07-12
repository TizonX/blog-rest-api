package com.blogAPI.Blog.controller;

import com.blogAPI.Blog.payload.CategoryDto;
import com.blogAPI.Blog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // add category REST API

    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto)
    {

      CategoryDto categoryDto1 =   categoryService.addCategory(categoryDto);

        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);

    }

    @GetMapping({"/{id}"})
    public  ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") Long id)
    {
      CategoryDto categoryDto =  categoryService.getCategory(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory()
    {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PutMapping({"/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, id));
    }

    @SecurityRequirement(name = "Bear Authentication")
    @DeleteMapping({"/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id)
    {
        categoryService.deleteCategory(id);

        return ResponseEntity.ok("Category Deleted Successfully");
    }
}
