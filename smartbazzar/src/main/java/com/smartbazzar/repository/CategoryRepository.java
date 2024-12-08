package com.smartbazzar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbazzar.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}