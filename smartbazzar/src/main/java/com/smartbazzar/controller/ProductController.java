package com.smartbazzar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartbazzar.entity.Product;
import com.smartbazzar.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	 @Autowired
	    private ProductService productService;
	 	@GetMapping("/test")
	    public ResponseEntity<String> testconn() {
	       
	        return ResponseEntity.ok("Up");
	    }

	    @GetMapping
	    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
	        Page<Product> products = productService.getAllProducts(pageable);
	        return ResponseEntity.ok(products);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        Optional<Product> product = productService.getProductById(id);
	        if (product.isPresent()) {
	            return ResponseEntity.ok(product.get());
	        }
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }

	    @PostMapping("/createProduct")
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        Product createdProduct = productService.createProduct(product);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
	        try {
	            Product updatedProduct = productService.updateProduct(id, product);
	            return ResponseEntity.ok(updatedProduct);
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return ResponseEntity.noContent().build();
	    }
}
