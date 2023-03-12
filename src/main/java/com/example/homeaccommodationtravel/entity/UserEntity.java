package com.example.homeaccommodationtravel.entity;

import lombok.Data;

@Data
public class UserEntity {

    private Integer userId;

    private String userPhone;

    private String userImg;

    private String userNickname;

    private String userPassword;

    private String userSalt;

    private String userSex;

    private Integer userAge;
}
