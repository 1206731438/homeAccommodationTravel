package com.example.homeaccommodationtravel.service;

import com.example.homeaccommodationtravel.po.HomeInsertPo;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.vo.HomeListVo;
import com.example.homeaccommodationtravel.vo.TypeNameList;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<HomeListVo> selectPage(Page page);

    /**
     * 添加房间
     * @param homeInsertPo
     * @param adminId
     * @return
     */
    Integer insert(HomeInsertPo homeInsertPo, Integer adminId);

    /**
     * 修改房间
     * @param homeInsertPo
     * @param adminId
     * @return
     */
    Integer update(HomeInsertPo homeInsertPo, Integer adminId);

    /**
     * 删除/批量删除
     * @param homeIds
     * @return
     */
    Integer del(List<Integer> homeIds);

    /**
     * 查询类型下拉框
     * @return
     */
    List<TypeNameList> getTypeNameList();

    /**
     * 用户界面分页查询
     * @param page
     * @return
     */
    PageInfo<HomeListVo> selUserPage(Page page);
}
