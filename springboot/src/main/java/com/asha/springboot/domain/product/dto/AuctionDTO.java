package com.asha.springboot.domain.product.dto;

import com.asha.springboot.domain.product.entity.AuctionEntity;
import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.product.entity.AuctionStatus;

import com.asha.springboot.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class AuctionDTO {
    private Long auctionId;            // 경매 ID
    private Long productId;            // 상품 ID (외래키)
    private BigDecimal buyNowPrice;    // 즉시 구매 가격
    private BigDecimal startPrice;     // 시작 가격
    private BigDecimal nowPrice;       // 현재 가격
    private BigDecimal endPrice;       // 낙찰 가격
    private LocalDateTime startAuctionTime; // 경매 시작 시간
    private LocalDateTime endAuctionTime;   // 경매 종료 시간
    private String status;             // 경매 상태
    private Long sellerId;             // 판매자 ID

    @Builder
    public AuctionDTO(Long auctionId, Long productId, BigDecimal buyNowPrice,
                      BigDecimal startPrice, BigDecimal nowPrice, BigDecimal endPrice,
                      LocalDateTime startAuctionTime, LocalDateTime endAuctionTime,
                      String status, Long sellerId) {
        this.auctionId = auctionId;
        this.productId = productId;
        this.buyNowPrice = buyNowPrice;
        this.startPrice = startPrice;
        this.nowPrice = nowPrice;
        this.endPrice = endPrice;
        this.startAuctionTime = startAuctionTime;
        this.endAuctionTime = endAuctionTime;
        this.status = status;
        this.sellerId = sellerId;
    }

    public AuctionEntity toEntity(ProductEntity productEntity, UserEntity sellerEntity) {
        return AuctionEntity.builder()
                .auctionId(auctionId)
                .product(productEntity)  // ProductEntity를 사용하여 설정
                .buyNowPrice(buyNowPrice)
                .startPrice(startPrice)
                .nowPrice(nowPrice)
                .endPrice(endPrice)
                .startAuctionTime(startAuctionTime)
                .endAuctionTime(endAuctionTime)
                .status(AuctionStatus.valueOf(status)) // Enum으로 변환
                .seller(sellerEntity)   // UserEntity를 사용하여 설정
                .build();
    }
}
