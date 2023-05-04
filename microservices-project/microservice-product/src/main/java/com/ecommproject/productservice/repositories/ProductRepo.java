package com.ecommproject.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommproject.productservice.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
