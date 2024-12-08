package com.smartbazzar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbazzar.entity.Product;



public interface ProductRepository extends JpaRepository<Product, Long> {}