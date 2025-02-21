package com.asha.springboot.domain.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 입찰 정보 엔티티
 */
@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BidEntity {

    // 입찰 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidId;

    // 경매 ID (외래키)
    @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩 설정
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    // 입찰 금액
    @Column(name = "bid_price", nullable = false)
    private BigDecimal bidPrice;

    // 입찰 시간
    @Column(name = "bid_time", nullable = false)
    private LocalDateTime bidTime;

    // 입찰자
    @Column(name = "customer", nullable = false)
    private String customer;

    @Builder
    public BidEntity(Long bidId, AuctionEntity auction, BigDecimal bidPrice, LocalDateTime bidTime, String customer) {
        this.bidId = bidId;
        this.auction = auction;
        this.bidPrice = bidPrice;
        this.bidTime = bidTime;
        this.customer = customer;
    }

}
