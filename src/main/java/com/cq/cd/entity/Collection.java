package com.cq.cd.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Collection {
    private Integer collectionId;
    private String email;
    private Integer postId;
    private LocalDateTime collectionData;
}
