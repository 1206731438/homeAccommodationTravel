package com.example.homeaccommodationtravel.utils;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtil {

    private Long pageNum;

    private List<Object> objList;

    public Long getTotalPage() {
        return pageNum;
    }

    public void setTotalPage(Long totalPage) {
        this.pageNum = totalPage;
    }

    public List<Object> getObjList() {
        return objList;
    }

    public void setObjList(List<Object> objList) {
        this.objList = objList;
    }

    public PageUtil() {
    }

    public PageUtil(Long totalPage, List<Object> objList) {
        this.pageNum = totalPage;
        this.objList = objList;
    }

    public static PageUtil ResultPage(PageInfo pageInfo) {
        return new PageUtil(pageInfo.getTotal(),pageInfo.getList());
    }
}
