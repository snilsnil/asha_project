package com.asha.springboot.domain.product.dto;

import com.asha.springboot.domain.product.entity.BidEntity;
import com.asha.springboot.domain.product.entity.AuctionEntity;
import com.asha.springboot.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class BidDTO {
    private Long bidId;           // 입찰 ID
    private Long auctionId;       // 경매 ID (외래키)
    private Long customerId;      // 입찰한 사용자 ID
    private BigDecimal bidPrice;  // 입찰 금액
    private LocalDateTime bidTime; // 입찰 시간

    // BidDTO에서 BidEntity로 변환
    public BidEntity toEntity(AuctionEntity auctionEntity, UserEntity userEntity) {
        return BidEntity.builder()
                .bidId(bidId)
                .auction(auctionEntity)  // AuctionEntity를 사용하여 설정
                .bidPrice(bidPrice)
                .bidTime(bidTime)
                .user(userEntity)        // UserEntity를 설정
                .build();
    }
}
