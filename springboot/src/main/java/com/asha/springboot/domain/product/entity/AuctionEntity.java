package com.asha.springboot.domain.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 경매 정보 엔티티
 */
@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionEntity {

    // 경매 ID
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;

    // 상품 ID , 외래키
    @OneToOne(fetch = FetchType.EAGER) // 즉시 로딩으로 설정
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    // 즉시 구매 가격 (즉시 낙찰가)
    @Column(name = "buy_now_price", nullable = false)
    private BigDecimal buyNowPrice;

    // 시작 가격
    @Column(name = "start_price", nullable = false)
    private BigDecimal startPrice;

    // 현재 가격
    @Column(name = "now_price")
    private BigDecimal nowPrice;

    // 낙찰 가격 (최종 입찰가)
    @Column(name = "end_price")
    private BigDecimal endPrice;

    // 경매 시작 시간
    @Column(name = "start_auction_time", nullable = false)
    private LocalDateTime startAuctionTime;

    // 경매 종료 시간
    @Column(name = "end_auction_time", nullable = false)
    private LocalDateTime endAuctionTime;

    // 진행 중, 종료됨 등
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;

    // 판매자
    @Column(name = "seller", nullable = false)
    private String seller;

    @Builder
    public AuctionEntity(
            Long auctionId,
            ProductEntity product,
            BigDecimal buyNowPrice,
            BigDecimal startPrice,
            BigDecimal nowPrice,
            BigDecimal endPrice,
            LocalDateTime startAuctionTime,
            LocalDateTime endAuctionTime,
            AuctionStatus status,
            String seller) {
        this.auctionId = auctionId;
        this.product = product;
        this.buyNowPrice = buyNowPrice;
        this.startPrice = startPrice;
        this.nowPrice = nowPrice;
        this.endPrice = endPrice;
        this.startAuctionTime = startAuctionTime;
        this.endAuctionTime = endAuctionTime;
        this.status = status;
        this.seller = seller;
    }
}
