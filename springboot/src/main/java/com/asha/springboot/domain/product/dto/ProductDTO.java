package com.asha.springboot.domain.product.dto;

import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.product.entity.CategoryEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDTO {
    private String productName; // 상품 이름
    private String description; // 상품 설명
    private String categories; // 카테고리 리스트
    private List<CategoryEntity> categoryEntities; // 카테고리 리스트
    private String imageUrl; // 상품 이미지 URL

    @Builder
    public ProductDTO(String productName, String description, String categories,
            String imageUrl) {
        this.productName = productName;
        this.description = description;
        this.categories = categories; // List<CategoryEntity로 변경
        this.imageUrl = imageUrl;
    }

    public ProductEntity toEntity(List<CategoryEntity> categoriyEntities) {
        // List<CategoryEntity> categoryEntities=List<CategoryDTO>.toCategoryEntities();
        return ProductEntity.builder()
                .productName(productName)
                .description(description)
                .categories(
                        categoriyEntities) // List<CategoryEntity 사용
                .imageUrl(imageUrl)
                .build();
    }
}
