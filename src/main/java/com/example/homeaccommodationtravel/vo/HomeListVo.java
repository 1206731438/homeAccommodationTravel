package com.example.homeaccommodationtravel.vo;

import com.example.homeaccommodationtravel.entity.HomeEntity;
import lombok.Data;

import java.util.List;

@Data
public class HomeListVo extends HomeEntity {

    private String typeName;

    private String homeCreateName;

    private String homeUpdateName;

    private List<String> imgs;
}
