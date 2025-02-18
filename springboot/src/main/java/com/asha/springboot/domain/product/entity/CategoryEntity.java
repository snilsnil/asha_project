package com.asha.springboot.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@NoArgsConstructor // 기본 생성자 추가 (JPA 요구사항)
@AllArgsConstructor // 모든 필드를 받는 생성자 추가 (빌더 패턴과 함께 사용)
@Builder
public class CategoryEntity {

    // 카테고리 ID (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    @Column(name = "category_id")
    private Long categoryId;

    // 카테고리명
    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

}
