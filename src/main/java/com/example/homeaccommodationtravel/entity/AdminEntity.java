package com.example.homeaccommodationtravel.entity;

import lombok.Data;

@Data
public class AdminEntity {

    //主键id
    private Integer adminId;

    //姓名
    private String adminName;

    //年龄
    private Integer adminAge;

    //性别
    private String adminSex;

    //角色
    private Integer adminType;

    //头像
    private String adminImg;

    //创建时间
    private String adminCreateDate;

    //创建人id
    private Integer adminCreateId;

    //更新时间
    private String adminUpdateDate;

    //更新人id
    private Integer adminUpdateId;

    //密码
    private String adminPassword;

    //密钥
    private String adminSalt;

    //手机号/账号
    private String adminPhone;

}
