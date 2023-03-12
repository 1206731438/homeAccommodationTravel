package com.example.homeaccommodationtravel.vo;

import com.example.homeaccommodationtravel.entity.OrderEntity;
import lombok.Data;

@Data
public class OrderListVo extends OrderEntity {

    private String orderStatusName;

    private String homeName;

    private Double homeMoney;

}



