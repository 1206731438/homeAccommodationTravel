package com.example.homeaccommodationtravel.vo;

import com.example.homeaccommodationtravel.entity.UserEntity;
import lombok.Data;

@Data
public class UserVo extends UserEntity {

    private String userToken;
}
