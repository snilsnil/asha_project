package com.asha.springboot.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asha.springboot.domain.product.entity.AuctionEntity;

public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {
}
