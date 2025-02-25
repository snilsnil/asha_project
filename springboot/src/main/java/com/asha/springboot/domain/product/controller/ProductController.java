// package com.asha.springboot.domain.product.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import com.asha.springboot.domain.product.service.ProductService;
// import com.asha.springboot.domain.product.dto.ProductDTO;
// import lombok.RequiredArgsConstructor;
// import java.util.List;

// @RestController
// @RequestMapping("/products")
// public class ProductController {

//     private final ProductService productService;

//     public ProductController(ProductService productService) {
//         this.productService = productService;
//     }

//     // 상품 생성
//     @PostMapping
//     public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
//         ProductDTO createdProduct = productService.createProduct(productDTO);
//         return ResponseEntity.status(201).body(createdProduct);
//     }

//     // 상품 조회
//     @GetMapping("/{productId}")
//     public ResponseEntity<ProductDTO> readProduct(@PathVariable Long productId) {
//         ProductDTO product = productService.readProduct(productId);
//         return ResponseEntity.ok(product);
//     }

//     // 상품 수정
//     @PutMapping("/{productId}")
//     public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
//         ProductDTO updatedProduct = productService.updateProduct(productId, productDTO);
//         return ResponseEntity.ok(updatedProduct);
//     }

//     // 상품 삭제
//     @DeleteMapping("/{productId}")
//     public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
//         productService.deleteProduct(productId);
//         return ResponseEntity.noContent().build();
//     }

//     // 전체 상품 목록 조회
//     @GetMapping
//     public ResponseEntity<List<ProductDTO>> getAllProducts() {
//         List<ProductDTO> products = productService.getAllProducts();
//         return ResponseEntity.ok(products);
//     }

//     // 카테고리별 상품 조회
//     @GetMapping("/category/{categoryId}")
//     public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable Long categoryId) {
//         List<ProductDTO> products = productService.getProductsByCategory(categoryId);
//         return ResponseEntity.ok(products);
//     }
// }
