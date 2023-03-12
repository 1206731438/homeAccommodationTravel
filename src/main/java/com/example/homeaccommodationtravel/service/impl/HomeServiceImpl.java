package com.example.homeaccommodationtravel.service.impl;

import com.example.homeaccommodationtravel.mapper.HomeMapper;
import com.example.homeaccommodationtravel.po.HomeInsertPo;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.HomeService;
import com.example.homeaccommodationtravel.utils.Base64Util;
import com.example.homeaccommodationtravel.vo.HomeListVo;
import com.example.homeaccommodationtravel.vo.TypeNameList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public PageInfo<HomeListVo> selectPage(Page page) {
        if(page.getCurrentPage() != null && page.getPageSize() != 0) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<HomeListVo> homeListVos = homeMapper.selectPage(page.getSearch(),page.getTypeId());
        if (homeListVos!= null && homeListVos.size() != 0) {
            for (HomeListVo homeListVo : homeListVos) {
                String homeImgs = homeListVo.getHomeImgs();
                if (homeImgs != null && !homeImgs.equals("")) {
                    String[] split = homeImgs.split(";");
                    homeListVo.setImgs(Arrays.asList(split));
                }
            }
        }
        return new PageInfo<>(homeListVos);
    }

    @Override
    public PageInfo<HomeListVo> selUserPage(Page page) {
        if(page.getCurrentPage() != null && page.getPageSize() != 0) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<HomeListVo> homeListVos = homeMapper.selUserPage(page.getSearch(),page.getTypeId());
        if (homeListVos!= null && homeListVos.size() != 0) {
            for (HomeListVo homeListVo : homeListVos) {
                String homeImgs = homeListVo.getHomeImgs();
                if (homeImgs != null && !homeImgs.equals("")) {
                    String[] split = homeImgs.split(";");
                    homeListVo.setImgs(Arrays.asList(split));
                }
            }
        }
        return new PageInfo<>(homeListVos);
    }

    @Override
    public Integer insert(HomeInsertPo homeInsertPo, Integer adminId) {
        List<String> imgsBase64 = homeInsertPo.getImgsBase64();
        //将base64转为图片
        if (imgsBase64 != null && imgsBase64.size() !=0) {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < imgsBase64.size(); i++) {
                if (i != imgsBase64.size()-1) {
                    sb.append(Base64Util.base64ToByte(imgsBase64.get(i)));
                    sb.append(";");
                }else {
                    sb.append(Base64Util.base64ToByte(imgsBase64.get(i)));
                }
            }
            homeInsertPo.setHomeImgs(sb.toString());
        }
        //存储
        return homeMapper.insert(homeInsertPo,adminId);
    }

    @Override
    public Integer update(HomeInsertPo homeInsertPo, Integer adminId) {
        List<String> imgsBase64 = homeInsertPo.getImgsBase64();
        //将base64转为图片
        if (imgsBase64 != null && imgsBase64.size() !=0) {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < imgsBase64.size(); i++) {
                if (i != imgsBase64.size()-1) {
                    sb.append(Base64Util.base64ToByte(imgsBase64.get(i)));
                    sb.append(";");
                }else {
                    sb.append(Base64Util.base64ToByte(imgsBase64.get(i)));
                }
            }
            homeInsertPo.setHomeImgs(sb.toString());
        }
        //修改
        return homeMapper.update(homeInsertPo,adminId);
    }

    @Override
    public Integer del(List<Integer> homeIds) {
        return homeMapper.del(homeIds);
    }

    @Override
    public List<TypeNameList> getTypeNameList() {
        return homeMapper.getTypeNameList();
    }
}
