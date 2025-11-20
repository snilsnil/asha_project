package com.asha.springboot.domain.product.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asha.springboot.domain.product.entity.AuctionEntity;

public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {
    List<AuctionEntity> findByStartPrice(BigDecimal startPrice);
}
