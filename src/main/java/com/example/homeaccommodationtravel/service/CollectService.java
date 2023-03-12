package com.example.homeaccommodationtravel.service;

import com.example.homeaccommodationtravel.entity.CollectEntity;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.vo.CollectVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface CollectService {
    /**
     * 分页查询
     * @param page
     * @param userId
     * @return
     */
    PageInfo<CollectVo> selectPage(Page page, Integer userId);

    /**
     * 添加收藏
     * @param collectEntity
     * @param userId
     * @return
     */
    Integer insert(CollectEntity collectEntity, Integer userId);

    /**
     * 删除收藏
     * @param collectId
     * @return
     */
    Integer del(Integer collectId);
}
