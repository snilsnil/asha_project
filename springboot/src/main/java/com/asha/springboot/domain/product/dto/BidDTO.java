package com.asha.springboot.domain.product.dto;

import com.asha.springboot.domain.product.entity.BidEntity;
import com.asha.springboot.domain.product.entity.AuctionEntity;

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
public class BidDTO {
    private String customer; // 입찰한 사용자 ID
    private BigDecimal bidPrice; // 입찰 금액
    private LocalDateTime bidTime; // 입찰 시간

    @Builder
    public BidDTO(
            String customer, BigDecimal bidPrice, LocalDateTime bidTime) {
        this.customer = customer;
        this.bidPrice = bidPrice;
        this.bidTime = bidTime;
    }

    // BidDTO에서 BidEntity로 변환
    public BidEntity toEntity(AuctionEntity auction, String customer) {
        return BidEntity.builder()
                .auction(auction) // AuctionEntity를 사용하여 설정
                .bidPrice(bidPrice)
                .bidTime(bidTime)
                .customer(customer) // UserEntity를 설정
                .build();
    }
}
