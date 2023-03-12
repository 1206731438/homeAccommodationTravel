package com.example.homeaccommodationtravel.entity;

import lombok.Data;

@Data
public class OrderEntity {

    private Integer orderId;

    private String orderStatus;

    private Integer homeId;

    private Integer userId;

    private String orderUserName;

    private String orderUserPhone;

    private String orderUserCard;

    private String orderCreateDate;

    private String orderNumber;
}
