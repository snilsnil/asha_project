package com.asha.springboot.domain.product.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.asha.springboot.domain.product.entity.CategoryEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDTO {
    private Long categoryId; // 카테고리 ID
    private String categoryName; // 카테고리명

    @Builder
    public CategoryDTO(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public List<CategoryEntity> toCategoryEntities() {
        return Stream.of(categoryName.split(","))
                .map(String::trim)
                .map(name -> CategoryEntity.builder().categoryName(name).build())
                .collect(Collectors.toList());
    }
}
