package com.example.homeaccommodationtravel.publicClass;

import lombok.Data;

@Data
public class Page {

    private Integer currentPage;

    private Integer pageSize;

    private String typeId;

    private String search;
}
