package com.asha.springboot.domain.product.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 상품 정보를 담는 엔티티
 */

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {

    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name", nullable = false) // Null 허용하지 않음
    private String productName;

    @Column(name = "description", columnDefinition = "TEXT") // TEXT 타입으로 설정
    private String description;

    @Column(name = "start_price", nullable = false) // null 허용하지 않음
    private BigDecimal startPrice;

    @ManyToMany(fetch = FetchType.EAGER) // 즉시 로딩으로 설정
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> categories;

    @Column(name = "image_url")
    private String imageUrl;

    @Builder
    public ProductEntity(Long productId, String productName, String description, BigDecimal startPrice, List<CategoryEntity> categories, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice; // startPrice 초기화
        this.categories = categories;
        this.imageUrl = imageUrl;
    }
}
