package com.example.homeaccommodationtravel.service;

import com.example.homeaccommodationtravel.entity.HomeTypeEntity;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.vo.HomeTypeVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeTypeService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<HomeTypeVo> selectPage(Page page);

    /**
     * 添加类型
     * @param homeTypeEntity
     * @param adminId
     * @return
     */
    Integer insert(HomeTypeEntity homeTypeEntity, Integer adminId);

    /**
     * 修改类型
     * @param homeTypeEntity
     * @param adminId
     * @return
     */
    Integer update(HomeTypeEntity homeTypeEntity, Integer adminId);

    /**
     * 删除/批量删除
     * @param typeIds
     * @return
     */
    Integer del(List<Integer> typeIds);
}
