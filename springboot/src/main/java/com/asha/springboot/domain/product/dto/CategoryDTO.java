package com.asha.springboot.domain.product.dto;

import com.asha.springboot.domain.product.entity.CategoryEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryDTO {
    private Long categoryId;      // 카테고리 ID
    private String categoryName;   // 카테고리명

    @Builder
    public CategoryDTO(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public CategoryEntity toEntity() {
        return CategoryEntity.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }
}
