package com.asha.springboot.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.asha.springboot.domain.community.entity.CommunityEntity;
import com.asha.springboot.domain.community.repository.ComunityRepository;

@SpringBootTest
public class CommunityTest {

    @Autowired
    private ComunityRepository comunityRepository;

    @Test
    public void test() {

        CommunityEntity test1 = new CommunityEntity("tet1", "admin", "hello world");

        comunityRepository.save(test1);
    }

}
