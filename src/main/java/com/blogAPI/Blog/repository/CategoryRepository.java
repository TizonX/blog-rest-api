package com.blogAPI.Blog.repository;

import com.blogAPI.Blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
