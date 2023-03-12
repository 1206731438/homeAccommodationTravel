package com.example.homeaccommodationtravel.utils;

import java.util.HashMap;

public class OrderStatusUtil {

    public static String getStatusName (String status){
        HashMap<String,String> map = new HashMap<>();
        map.put("dzf","待支付");
        map.put("drz","待入住");
        map.put("yrz","已入住");
        map.put("ywc","已完成");
        map.put("dtd","待退订");
        map.put("ytd","已退订");
        map.put("ybh","已驳回");
        return map.get(status);
    }
}
