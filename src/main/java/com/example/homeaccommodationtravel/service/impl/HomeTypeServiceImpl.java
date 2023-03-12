package com.example.homeaccommodationtravel.service.impl;

import com.example.homeaccommodationtravel.entity.HomeTypeEntity;
import com.example.homeaccommodationtravel.mapper.HomeTypeMapper;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.HomeTypeService;
import com.example.homeaccommodationtravel.vo.HomeTypeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeTypeServiceImpl implements HomeTypeService {

    @Autowired
    private HomeTypeMapper homeTypeMapper;

    @Override
    public PageInfo<HomeTypeVo> selectPage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<HomeTypeVo> homeTypeVos = homeTypeMapper.selectPage(page.getSearch());
        return new PageInfo<>(homeTypeVos);
    }

    @Override
    public Integer insert(HomeTypeEntity homeTypeEntity, Integer adminId) {
        //根据类型名称查询类型是否存在
        List<HomeTypeEntity> homeTypeEntities = homeTypeMapper.selByTypeName(homeTypeEntity.getTypeName());
        if (homeTypeEntities != null && homeTypeEntities.size() != 0) {
            return 2;
        }
        return homeTypeMapper.insert(homeTypeEntity,adminId);
    }

    @Override
    public Integer update(HomeTypeEntity homeTypeEntity, Integer adminId) {
        //根据类型名查询是否存在
        List<HomeTypeEntity> homeTypeEntities = homeTypeMapper.selByTypeName(homeTypeEntity.getTypeName());
        if (homeTypeEntities != null && homeTypeEntities.size() > 0) {
            HomeTypeEntity homeType = homeTypeEntities.get(0);
            if (homeType.getTypeId() == homeTypeEntity.getTypeId()) {
                //说明名称没改
                return 1;
            }else {
                //说明已存在
                return 2;
            }
        }else {
            return homeTypeMapper.update(homeTypeEntity,adminId);
        }
    }

    @Override
    public Integer del(List<Integer> typeIds) {
        return homeTypeMapper.del(typeIds);
    }
}
