package com.asha.springboot.domain.product.dto;

import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.product.entity.CategoryEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ProductDTO {
    private Long productId;        // 상품 ID
    private String productName;    // 상품 이름
    private String description;     // 상품 설명
    private BigDecimal startPrice;  // 경매 시작 가격
    private List<CategoryEntity> categories; // 카테고리 리스트
    private String imageUrl;       // 상품 이미지 URL

    @Builder
    public ProductDTO(Long productId, String productName, String description, BigDecimal startPrice, List<CategoryEntity> categories, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice;
        this.categories = categories; // List<CategoryEntity로 변경
        this.imageUrl = imageUrl;
    }

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .productId(productId)
                .productName(productName)
                .description(description)
                .startPrice(startPrice)
                .categories(categories)  // List<CategoryEntity 사용
                .imageUrl(imageUrl)
                .build();
    }
}
