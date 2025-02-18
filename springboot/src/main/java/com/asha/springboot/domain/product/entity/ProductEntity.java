package com.asha.springboot.domain.product.entity;



import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/*### **1️⃣ ProductEntity (상품)**

| 컬럼명 | 타입 | 설명 |
| --- | --- | ---   |
| `product_id` | PK | 상품 ID |
| `product_name` | VARCHAR | 상품 이름 |
| `description` | TEXT | 상품 설명 |
| `start_price` | DECIMAL | 경매 시작 가격 |
| `category_id` | FK | 카테고리 ID (외래키) |
| `image_url` | VARCHAR | 상품 이미지 URL | */

@Getter
@ToString
@Entity
@Builder
public class ProductEntity {

    // 상품 ID
    @Id  // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; 

    // 상품 이름
    @Column(name = "product_name", nullable = false) // Null 허용하지 않음
    private String productName; 

    // 상품 설명
    @Column(name = "description", columnDefinition = "TEXT") // TEXT 타입으로 설정
    private String description;

    // 경매 시작 가격 
    // DECIMAL 타입을 BigDecimal로 매핑
    @Column(name = "start_price", nullable = false)
    private BigDecimal startPrice; 

    // 카테고리 ID (외래키)
    // 외래키 컬럼 설정 many-to-One 관계 (Category 엔티티와 연결)
    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "category_id") 
    private CategoryEntity category;

    // 상품 이미지 URL
    @Column(name = "image_url")
    private String imageUrl;

}
