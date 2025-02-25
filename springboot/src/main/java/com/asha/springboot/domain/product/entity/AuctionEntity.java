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
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(exclude = { "product", "seller" }) // 무한 참조 방지
@Entity
@Builder
public class AuctionEntity {

    // 경매 ID
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;

    // 상품 ID , 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    // 즉시 구매 가격 (즉시 낙찰가)
    @Column(name = "buy_now_price", nullable = false)
    private BigDecimal buyNowPrice;

    // 시작 가격
    @Column(name = "start_price", nullable = false)
    private BigDecimal startPrice;

    // 현재 가격
    @Column(name = "now_price", nullable = false)
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

    // 판매자 ID
    @Column(name = "seller", nullable = false)
    private String seller;
}
