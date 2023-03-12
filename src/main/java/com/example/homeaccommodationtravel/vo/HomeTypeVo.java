package com.example.homeaccommodationtravel.vo;

import com.example.homeaccommodationtravel.entity.HomeTypeEntity;
import lombok.Data;

@Data
public class HomeTypeVo extends HomeTypeEntity {

    private String typeCreateName;

    private String typeUpdateName;
}
