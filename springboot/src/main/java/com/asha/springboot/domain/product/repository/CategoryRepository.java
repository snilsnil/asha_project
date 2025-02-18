package com.asha.springboot.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asha.springboot.domain.product.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByCategoryName(String categoryName);

}
