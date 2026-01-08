package com.harish.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harish.ecommerce.model.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
