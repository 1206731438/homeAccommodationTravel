package com.example.homeaccommodationtravel.entity;

import lombok.Data;

@Data
public class CommentEntity {

    private Integer commentId;

    private Integer userId;

    private Integer homeId;

    private String commentContent;
}
