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
    private String writer;
    private String description;

    @Builder
    public CommunityEntity(
            String title, String writer, String description) {
        this.title = title;
        this.writer = writer;
        this.description = description;
    }

}