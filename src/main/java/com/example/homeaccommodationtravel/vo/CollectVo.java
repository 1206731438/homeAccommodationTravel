package com.example.homeaccommodationtravel.vo;

import com.example.homeaccommodationtravel.entity.CollectEntity;
import lombok.Data;

import java.util.List;

@Data
public class CollectVo extends CollectEntity {
    private String typeName;

    private String firstImg;

    private List<String> imgs;

    private Integer homeId;

    private Integer typeId;

    private Double homeMoney;

    private String homeName;

    private Integer homePeople;

    private String homeNetwork;

    private String homeRoom;

    private String homeDiet;

    private String homeClean;

    private String homeImgs;
}
