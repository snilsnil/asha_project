package com.asha.springboot.domain.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.asha.springboot.domain.user.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
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
@Builder
@NoArgsConstructor // 기본 생성자 추가 (JPA에서 요구)
@AllArgsConstructor // 모든 필드를 받는 생성자 추가
public class BidEntity {

    // 입찰 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidId;

    // 경매 ID (외래키)
    @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩 설정
    @JoinColumn(name = "auction_id")
    private AuctionEntity auctionId;

    // 입찰 금액
    @Column(name = "bid_price", nullable = false)
    private BigDecimal bidPrice;

    // 입찰 시간
    @Column(name = "bid_time", nullable = false)
    private LocalDateTime bidTime;

    // 입찰자 (사용자 ID)
    @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩 설정
    @JoinColumn(name = "customer_id")
    private UserEntity customer;
}
