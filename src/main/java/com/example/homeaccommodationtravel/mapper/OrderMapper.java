package com.example.homeaccommodationtravel.mapper;

import com.example.homeaccommodationtravel.entity.OrderEntity;
import com.example.homeaccommodationtravel.vo.OrderListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    /**
     * 分页查询
     * @param search
     * @return
     */
    List<OrderListVo> selectPage(@Param("search") String search);

    /**
     * 管理员取消订单
     * @param orderId
     * @return
     */
    Integer cancel(@Param("orderId") Integer orderId,@Param("orderStatus") String orderStatus);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    Integer del(@Param("orderId") Integer orderId);

    /**
     * 退订查询
     * @param search
     * @return
     */
    List<OrderListVo> unsubscribePage(@Param("search") String search);

    /**
     * 用户订单分页查询
     * @param search
     * @return
     */
    List<OrderListVo> selUserPage(@Param("search") String search,@Param("userId") Integer userId);

    /**
     * 添加预定
     * @param orderEntity
     * @return
     */
    Integer insertOrder(@Param("orderEntity") OrderEntity orderEntity);

    /**
     * 根据订单号修改订单状态
     * @param orderNumber
     */
    void updateStatusByOrderNomber(@Param("orderNumber") String orderNumber);
}
