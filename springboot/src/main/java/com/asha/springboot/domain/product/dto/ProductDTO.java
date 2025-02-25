package com.asha.springboot.domain.product.dto;

import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.product.entity.CategoryEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductDTO {
    private Long productId;        // 상품 ID
    private String productName;    // 상품 이름
    private String description;     // 상품 설명
    private BigDecimal startPrice;  // 경매 시작 가격
    private Long categoryId;       // 카테고리 ID
    private String imageUrl;       // 상품 이미지 URL

    @Builder
    public ProductDTO(Long productId, String productName, String description, BigDecimal startPrice, Long categoryId, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

    public ProductEntity toEntity(CategoryEntity categoryEntity) {
        return ProductEntity.builder()
                .productId(productId)
                .productName(productName)
                .description(description)
                .startPrice(startPrice)
                .category(categoryEntity)  // CategoryEntity를 사용하여 카테고리 설정
                .imageUrl(imageUrl)
                .build();
    }
}
