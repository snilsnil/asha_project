package com.asha.springboot.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asha.springboot.domain.product.entity.BidEntity;

public interface BidRepository extends JpaRepository<BidEntity, Long> {

}
