package com.asha.springboot.domain.product.dto;

import com.asha.springboot.domain.product.entity.AuctionEntity;
import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.product.entity.AuctionStatus;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionDTO {
    private BigDecimal buyNowPrice; // 즉시 구매 가격
    private BigDecimal startPrice; // 시작 가격
    private BigDecimal nowPrice; // 현재 가격
    private BigDecimal endPrice; // 낙찰 가격
    private LocalDateTime startAuctionTime; // 경매 시작 시간
    private LocalDateTime endAuctionTime; // 경매 종료 시간
    private AuctionStatus status; // 경매 상태
    private String seller; // 판매자 ID

    @Builder
    public AuctionDTO(BigDecimal buyNowPrice,
            BigDecimal startPrice, BigDecimal nowPrice, BigDecimal endPrice,
            LocalDateTime startAuctionTime, LocalDateTime endAuctionTime,
            AuctionStatus status, String seller) {
        this.buyNowPrice = buyNowPrice;
        this.startPrice = startPrice;
        this.nowPrice = nowPrice;
        this.endPrice = endPrice;
        this.startAuctionTime = startAuctionTime;
        this.endAuctionTime = endAuctionTime;
        this.status = status;
        this.seller = seller;
    }

    public AuctionEntity toEntity(ProductEntity product, String seller) {
        return AuctionEntity.builder()
                .product(product) // ProductEntity를 사용하여 설정
                .buyNowPrice(buyNowPrice)
                .startPrice(startPrice)
                .nowPrice(nowPrice)
                .endPrice(endPrice)
                .startAuctionTime(startAuctionTime)
                .endAuctionTime(endAuctionTime)
                .status(status) // Enum으로 변환
                .seller(seller) // UserEntity를 사용하여 설정
                .build();
    }
}
