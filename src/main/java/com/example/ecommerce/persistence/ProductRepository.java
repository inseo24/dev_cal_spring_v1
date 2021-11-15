package com.example.ecommerce.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
