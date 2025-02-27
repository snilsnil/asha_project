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

    @Builder
    public CategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    @Builder
    public CategoryDTO(List<CategoryDTO> categories) {
        this.categoryId = null; // 리스트 생성 시 개별 ID는 사용하지 않음
        this.categoryName = categories.stream()
                .map(CategoryDTO::getCategoryName)
                .collect(Collectors.joining(",")); // 문자열로 변환
    }

    public CategoryEntity toEntity() {
        return CategoryEntity.builder()
                .categoryName(categoryName).build();
    }

    public List<CategoryEntity> toCategoryEntities() {
        return Stream.of(categoryName.split(","))
                .map(String::trim)
                .map(name -> CategoryEntity.builder().categoryName(name).build())
                .collect(Collectors.toList());
    }

    public static List<CategoryEntity> toCategoryEntities(List<String> categories) {
        return categories.stream()
                .map(categoryName -> CategoryEntity.builder()
                        .categoryName(categoryName) // String을 categoryName에 할당
                        .build())
                .collect(Collectors.toList());
    }
}
