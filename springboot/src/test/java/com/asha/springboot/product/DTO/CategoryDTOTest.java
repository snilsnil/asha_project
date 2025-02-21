package com.asha.springboot.product.DTO;

import com.asha.springboot.domain.product.entity.CategoryEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDTOTest {
    private String categoryName;

    @Builder
    public CategoryDTOTest(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryEntity toCategoryEntity(String categoryName) {
        return CategoryEntity.builder()
                .categoryName(categoryName)
                .build();
    }
}
