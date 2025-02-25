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
    private List<CategoryEntity> categories; // 카테고리 리스트
    private String imageUrl; // 상품 이미지 URL

    @Builder
    public ProductDTO(String productName, String description, List<CategoryEntity> categories,
            String imageUrl) {
        this.productName = productName;
        this.description = description;
        this.categories = categories; // List<CategoryEntity로 변경
        this.imageUrl = imageUrl;
    }

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .productName(productName)
                .description(description)
                .categories(categories) // List<CategoryEntity 사용
                .imageUrl(imageUrl)
                .build();
    }
}
