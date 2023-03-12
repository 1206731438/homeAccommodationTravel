package com.example.homeaccommodationtravel.vo;

import lombok.Data;

@Data
public class CommentVo {

    private Integer commentId;

    private String commentContent;

    private Integer userId;

    private String userNickname;

    private String commentCreateTime;

    private String userImg;
}
