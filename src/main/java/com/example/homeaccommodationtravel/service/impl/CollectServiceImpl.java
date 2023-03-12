package com.example.homeaccommodationtravel.service.impl;

import com.example.homeaccommodationtravel.entity.CollectEntity;
import com.example.homeaccommodationtravel.mapper.CollectMapper;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.CollectService;
import com.example.homeaccommodationtravel.vo.CollectVo;
import com.example.homeaccommodationtravel.vo.HomeListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public PageInfo<CollectVo> selectPage(Page page, Integer userId) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<CollectVo> collectVos = collectMapper.selectPage(page.getSearch(),userId);
        if (collectVos!= null && collectVos.size() != 0) {
            for (CollectVo collectVo : collectVos) {
                String homeImgs = collectVo.getHomeImgs();
                if (homeImgs != null && !homeImgs.equals("")) {
                    String[] split = homeImgs.split(";");
                    collectVo.setFirstImg(split[0]);
                    collectVo.setImgs(Arrays.asList(split));
                }
            }
        }
        return new PageInfo<>(collectVos);
    }

    @Override
    public Integer insert(CollectEntity collectEntity, Integer userId) {
        //根据homeId和userId查询用户是否已经收藏
        List<Integer> i = collectMapper.selByUserIdAndHomeId(collectEntity.getHomeId(),userId);
        if (i != null && i.size() > 0) {
            return 2;
        }
        return collectMapper.insert(collectEntity,userId);
    }

    @Override
    public Integer del(Integer collectId) {
        return collectMapper.del(collectId);
    }
}
