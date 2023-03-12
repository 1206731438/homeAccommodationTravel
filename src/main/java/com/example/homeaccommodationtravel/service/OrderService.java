package com.example.homeaccommodationtravel.service;

import com.example.homeaccommodationtravel.entity.OrderEntity;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.vo.OrderListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<OrderListVo> selectPage(Page page);

    /**
     * 管理员取消订单
     * @param orderEntity
     * @return
     */
    Integer cancel(OrderEntity orderEntity);

    /**
     * 删除订单
     * @param orderEntity
     * @return
     */
    Integer del(OrderEntity orderEntity);

    /**
     * 退订查询
     * @param page
     * @return
     */
    PageInfo<OrderListVo> unsubscribePage(Page page);

    /**
     * 用户分页查询
     * @param page
     * @return
     */
    PageInfo<OrderListVo> selUserPage(Page page,Integer userId);

    /**
     * 添加预定
     * @param orderEntity
     * @return
     */
    Integer insertOrder(OrderEntity orderEntity);
}
