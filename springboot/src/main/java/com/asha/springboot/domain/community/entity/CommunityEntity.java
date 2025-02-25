package com.asha.springboot.domain.community.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Document
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityEntity {

    private String title;
    private String writter;
    private String descrition;

    @Builder
    public CommunityEntity(
            String title, String writter, String decription) {
        this.title = title;
        this.writter = writter;
        this.descrition = decription;
    }

}