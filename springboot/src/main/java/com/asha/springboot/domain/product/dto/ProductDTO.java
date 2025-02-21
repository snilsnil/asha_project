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

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 정보 DTO (테스트용)
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

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

    @Builder
    public ProductDTO(
            String productName,
            String description,
            String imageUrl,
            String categoryName,
            BigDecimal buyNowPrice,
            BigDecimal startPrice,
            BigDecimal nowPrice,
            BigDecimal endPrice,
            LocalDateTime startAuctionTime,
            LocalDateTime endAuctionTime,
            AuctionStatus status,
            BigDecimal bidPrice,
            LocalDateTime bidTime) {
        this.productName = productName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryName = categoryName;
        this.buyNowPrice = buyNowPrice;
        this.startPrice = startPrice;
        this.nowPrice = nowPrice;
        this.endPrice = endPrice;
        this.startAuctionTime = startAuctionTime;
        this.endAuctionTime = endAuctionTime;
        this.status = status;
        this.bidPrice = bidPrice;
        this.bidTime = bidTime;
    }

    public ProductEntity toProductEntity(List<CategoryEntity> categories) {
        return ProductEntity.builder()
                .productName(productName)
                .description(description)
                .imageUrl(imageUrl)
                .categories(categories)
                .build();
    }

    public AuctionEntity toAuctionEntity(ProductEntity productEntity, String seller) {
        return AuctionEntity.builder()
                .product(productEntity)
                .buyNowPrice(buyNowPrice)
                .startPrice(startPrice)
                .nowPrice(nowPrice)
                .endPrice(endPrice)
                .startAuctionTime(startAuctionTime)
                .endAuctionTime(endAuctionTime)
                .status(status)
                .seller(seller)
                .build();
    }

    public BidEntity toBidEntity(AuctionEntity auction, String customer) {
        return BidEntity.builder()
                .auction(auction)
                .bidPrice(bidPrice)
                .bidTime(bidTime)
                .customer(customer)
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
