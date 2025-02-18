package com.asha.springboot.domain.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.asha.springboot.domain.product.entity.AuctionEntity;
import com.asha.springboot.domain.product.entity.AuctionStatus;
import com.asha.springboot.domain.product.entity.BidEntity;
import com.asha.springboot.domain.product.entity.CategoryEntity;
import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.user.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 정보 DTO (테스트용)
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private String productName;
    private String description;
    private String imageUrl;

    private String categoryName;

    private BigDecimal buyNowPrice;
    private BigDecimal startPrice;
    private BigDecimal nowPrice;
    private BigDecimal endPrice;
    private LocalDateTime startAuctionTime;
    private LocalDateTime endAuctionTime;
    private AuctionStatus status;

    private BigDecimal bidPrice;
    private LocalDateTime bidTime;

    public ProductEntity toProductEntity(List<CategoryEntity> categories) {
        return ProductEntity.builder()
                .productName(productName)
                .description(description)
                .imageUrl(imageUrl)
                .categories(categories)
                .build();
    }

    public AuctionEntity toAuctionEntity(ProductEntity productEntity, UserEntity user) {
        return AuctionEntity.builder()
                .product(productEntity)
                .buyNowPrice(buyNowPrice)
                .startPrice(startPrice)
                .nowPrice(nowPrice)
                .endPrice(endPrice)
                .startAuctionTime(startAuctionTime)
                .endAuctionTime(endAuctionTime)
                .status(status)
                .sellerId(user)
                .build();
    }

    public BidEntity toBidEntity(AuctionEntity auctionEntity, UserEntity user) {
        return BidEntity.builder()
                .auctionId(auctionEntity)
                .bidPrice(bidPrice)
                .bidTime(bidTime)
                .customer(user)
                .build();
    }

    /**
     * 카테고리명을 CategoryEntity 리스트로 변환
     * 
     * @return 카테고리 엔티티 리스트
     */
    public List<CategoryEntity> toCategoryEntities() {
        return Stream.of(categoryName.split(","))
                .map(String::trim)
                .map(name -> CategoryEntity.builder().categoryName(name).build())
                .collect(Collectors.toList());
    }
}
