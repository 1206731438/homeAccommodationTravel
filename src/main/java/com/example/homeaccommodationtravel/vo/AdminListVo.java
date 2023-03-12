package com.example.homeaccommodationtravel.vo;

import com.example.homeaccommodationtravel.entity.AdminEntity;
import lombok.Data;

@Data
public class AdminListVo extends AdminEntity {

    private String adminCreateName;

    private String adminUpdateName;

    private String adminTypeName;
}
