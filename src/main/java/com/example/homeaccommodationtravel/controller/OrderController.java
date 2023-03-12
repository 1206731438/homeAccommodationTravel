package com.example.homeaccommodationtravel.controller;

import com.example.homeaccommodationtravel.entity.OrderEntity;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.OrderService;
import com.example.homeaccommodationtravel.utils.JWTUtil;
import com.example.homeaccommodationtravel.utils.PageUtil;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.OrderListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 分页查询
     * @param request
     * @param page
     * @return
     */
    @PostMapping("/selectPage")
    public ResultUtil selectPage(HttpServletRequest request, @RequestBody Page page) {
        //判断是否登录
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(adminToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        PageInfo<OrderListVo> pageInfo = orderService.selectPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    /**
     * 用户订单分页查询
     * @param request
     * @param page
     * @return
     */
    @PostMapping("/selUserPage")
    public ResultUtil selUserPage(HttpServletRequest request, @RequestBody Page page) {
        //判断是否登录
        String userToken = request.getHeader("userToken");
        if (userToken == null || userToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(userToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer userId = JWTUtil.verifyUserToken(userToken);
        PageInfo<OrderListVo> pageInfo = orderService.selUserPage(page,userId);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    /**
     * 管理员操作订单
     * @param request
     * @param orderEntity
     * @return
     */
    @PostMapping("/cancel")
    public ResultUtil cancel(HttpServletRequest request, @RequestBody OrderEntity orderEntity) {
        //判断是否登录
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(adminToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = orderService.cancel(orderEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("操作失败");
        }
        return ResultUtil.respinseSuccess("操作成功");
    }

    /**
     * 用户操作订单
     * @param request
     * @param orderEntity
     * @return
     */
    @PostMapping("/userCancel")
    public ResultUtil userCancel(HttpServletRequest request, @RequestBody OrderEntity orderEntity) {
        //判断是否登录
        String userToken = request.getHeader("userToken");
        if (userToken == null || userToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(userToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = orderService.cancel(orderEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("操作失败");
        }
        return ResultUtil.respinseSuccess("操作成功");
    }

    /**
     * 删除订单
     * @param request
     * @param orderEntity
     * @return
     */
    @PostMapping("/del")
    public ResultUtil del(HttpServletRequest request, @RequestBody OrderEntity orderEntity) {
        //判断是否登录
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(adminToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = orderService.del(orderEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("删除失败");
        }
        return ResultUtil.respinseSuccess("删除成功");
    }

    /**
     * 退订查询
     * @param request
     * @param page
     * @return
     */
    @PostMapping("/unsubscribePage")
    public ResultUtil unsubscribePage(HttpServletRequest request, @RequestBody Page page) {
        //判断是否登录
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(adminToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        PageInfo<OrderListVo> pageInfo = orderService.unsubscribePage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    /**
     * 用户添加预定
     * @return
     */
    @PostMapping("/insertOrder")
    public ResultUtil insertOrder(HttpServletRequest request,@RequestBody OrderEntity orderEntity) {
        //判断是否登录
        String userToken = request.getHeader("userToken");
        if (userToken == null || userToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(userToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer userId = JWTUtil.verifyUserToken(userToken);
        orderEntity.setUserId(userId);
        Integer i = orderService.insertOrder(orderEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("预定失败");
        }
        return ResultUtil.respinseSuccess("预定成功");
    }
}
