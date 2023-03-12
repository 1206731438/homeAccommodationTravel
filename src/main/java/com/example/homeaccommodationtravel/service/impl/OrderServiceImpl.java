package com.example.homeaccommodationtravel.service.impl;

import com.example.homeaccommodationtravel.entity.OrderEntity;
import com.example.homeaccommodationtravel.mapper.OrderMapper;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.OrderService;
import com.example.homeaccommodationtravel.utils.OrderStatusUtil;
import com.example.homeaccommodationtravel.vo.OrderListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<OrderListVo> selectPage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<OrderListVo> orderListVos = orderMapper.selectPage(page.getSearch());
        if (orderListVos != null && orderListVos.size() > 0) {
            for (OrderListVo orderListVo : orderListVos) {
                if (orderListVo.getOrderStatus() != null && !orderListVo.getOrderStatus().equals("")) {
                    orderListVo.setOrderStatusName(OrderStatusUtil.getStatusName(orderListVo.getOrderStatus()));
                }
            }
        }

        return new PageInfo<>(orderListVos);
    }

    @Override
    public Integer cancel(OrderEntity orderEntity) {
        return orderMapper.cancel(orderEntity.getOrderId(),orderEntity.getOrderStatus());
    }

    @Override
    public Integer del(OrderEntity orderEntity) {
        return orderMapper.del(orderEntity.getOrderId());
    }

    @Override
    public PageInfo<OrderListVo> unsubscribePage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<OrderListVo> orderListVos = orderMapper.unsubscribePage(page.getSearch());
        if (orderListVos != null && orderListVos.size() > 0) {
            for (OrderListVo orderListVo : orderListVos) {
                if (orderListVo.getOrderStatus() != null && !orderListVo.getOrderStatus().equals("")) {
                    orderListVo.setOrderStatusName(OrderStatusUtil.getStatusName(orderListVo.getOrderStatus()));
                }
            }
        }
        return new PageInfo<>(orderListVos);
    }

    @Override
    public PageInfo<OrderListVo> selUserPage(Page page,Integer userId) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<OrderListVo> orderListVos = orderMapper.selUserPage(page.getSearch(),userId);
        if (orderListVos != null && orderListVos.size() > 0) {
            for (OrderListVo orderListVo : orderListVos) {
                if (orderListVo.getOrderStatus() != null && !orderListVo.getOrderStatus().equals("")) {
                    orderListVo.setOrderStatusName(OrderStatusUtil.getStatusName(orderListVo.getOrderStatus()));
                }
            }
        }
        return new PageInfo<>(orderListVos);
    }

    @Override
    public Integer insertOrder(OrderEntity orderEntity) {
        //生成订单号
        String orderNumber = UUID.randomUUID().toString();
        orderEntity.setOrderNumber(orderNumber);
        return orderMapper.insertOrder(orderEntity);
    }
}
