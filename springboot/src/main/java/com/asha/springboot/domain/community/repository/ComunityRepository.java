package com.asha.springboot.domain.community.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.asha.springboot.domain.community.entity.CommunityEntity;

public interface ComunityRepository extends MongoRepository<CommunityEntity, String> {

}
