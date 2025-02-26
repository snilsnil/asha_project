package com.asha.springboot.domain.product.controller;

import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products") // 기본 경로
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 등록
    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        ProductEntity createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // 특정 상품 조회
    @GetMapping("/{productId}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long productId) {
        Optional<ProductEntity> product = productService.getProductById(productId);
        return product.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 전체 상품 조회
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long productId, @RequestBody ProductEntity updatedProduct) {
        try {
            ProductEntity product = productService.updateProduct(productId, updatedProduct);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
