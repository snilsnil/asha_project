package com.asha.springboot.domain.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.asha.springboot.domain.product.entity.ProductEntity;  
import com.asha.springboot.domain.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 상품 등록
    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    // 특정 상품 조회
    public Optional<ProductEntity> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    // 전체 상품 조회
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    // 상품 수정
    public ProductEntity updateProduct(Long productId, ProductEntity updatedProduct) {
        return productRepository.findById(productId)
            .map(product -> {
                product.updateProduct(updatedProduct.getProductName(), updatedProduct.getDescription(), updatedProduct.getImageUrl());
                return productRepository.save(product);
            })
            .orElseThrow(() -> new RuntimeException("상품이 없습니다."));
    }
    

    // 상품 삭제
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
