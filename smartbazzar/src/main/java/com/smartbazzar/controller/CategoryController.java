package com.smartbazzar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartbazzar.entity.Category;
import com.smartbazzar.entity.Product;
import com.smartbazzar.service.CategoryService;
import com.smartbazzar.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	 @Autowired
	    private CategoryService categoryService;

	    @GetMapping
	    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable) {
	        Page<Category> categories = categoryService.getAllCategories(pageable);
	        return ResponseEntity.ok(categories);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	        Optional<Category> category = categoryService.getCategoryById(id);
	        if (category.isPresent()) {
	            return ResponseEntity.ok(category.get());
	        }
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }

	    @PostMapping("/createCategory")
	    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	    	System.out.println("In create categiry");
	        Category createdCategory = categoryService.createCategory(category);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
	        try {
	            Category updatedCategory = categoryService.updateCategory(id, category);
	            return ResponseEntity.ok(updatedCategory);
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	        categoryService.deleteCategory(id);
	        return ResponseEntity.noContent().build();
	    }
}
