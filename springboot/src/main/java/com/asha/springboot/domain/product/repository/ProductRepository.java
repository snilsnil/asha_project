package com.asha.springboot.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asha.springboot.domain.product.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity findByProductName(String productName);

    Long findByProductId(Long productId);

}
