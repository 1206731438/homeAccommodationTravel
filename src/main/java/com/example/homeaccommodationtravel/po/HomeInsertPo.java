package com.example.homeaccommodationtravel.po;

import com.example.homeaccommodationtravel.entity.HomeEntity;
import lombok.Data;

import java.util.List;

@Data
public class HomeInsertPo extends HomeEntity {

    private List<String> imgsBase64;

}
