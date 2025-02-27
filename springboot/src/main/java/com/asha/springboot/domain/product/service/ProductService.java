package com.asha.springboot.domain.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asha.springboot.domain.product.dto.CategoryDTO;
import com.asha.springboot.domain.product.dto.ProductDTO;
import com.asha.springboot.domain.product.entity.CategoryEntity;
import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.product.repository.CategoryRepository;
import com.asha.springboot.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // 상품 등록
    @Transactional
    public ProductEntity createProduct(ProductDTO product) {

        // 카테고리 문자열을 분리하여 리스트로 변환
        List<String> categoryList = Arrays.asList(product.getCategories().split(","));

        // 카테고리 엔티티 리스트를 저장할 변수
        List<CategoryEntity> categoryEntities = new ArrayList<>();

        // 카테고리 리스트를 순회하며 존재 여부를 체크
        for (String category : categoryList) {
            CategoryEntity existingCategory = categoryRepository.findByCategoryName(category);

            if (existingCategory == null) {
                // 카테고리가 없으면 새로 생성 후 저장
                CategoryDTO categoryDTO = new CategoryDTO(category); // categoryName을 가진 DTO 생성
                CategoryEntity categoryEntity = categoryDTO.toEntity(); // DTO -> Entity 변환
                existingCategory = categoryRepository.save(categoryEntity); // 카테고리 저장
            }

            // 카테고리를 엔티티 리스트에 추가
            categoryEntities.add(existingCategory);
        }

        // ProductEntity로 변환
        ProductEntity productEntity = product.toEntity(categoryEntities);

        // 최종적으로 제품과 카테고리들을 저장
        return productRepository.save(productEntity);
    }

    // 특정 상품 조회
    @Transactional
    public Optional<ProductEntity> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    // 전체 상품 조회
    @Transactional
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    // 상품 수정
    @Transactional
    public ProductEntity updateProduct(Long productId, ProductEntity updatedProduct) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.updateProduct(updatedProduct.getProductName(), updatedProduct.getDescription(),
                            updatedProduct.getImageUrl());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("상품이 없습니다."));
    }

    // 상품 삭제
    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
