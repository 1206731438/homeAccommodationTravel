package com.example.homeaccommodationtravel.controller;

import com.example.homeaccommodationtravel.entity.CollectEntity;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.CollectService;
import com.example.homeaccommodationtravel.utils.JWTUtil;
import com.example.homeaccommodationtravel.utils.PageUtil;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.CollectVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping("/selectPage")
    public ResultUtil selectPage(HttpServletRequest request, @RequestBody Page page) {
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
        PageInfo<CollectVo> pageInfo = collectService.selectPage(page,userId);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    @PostMapping("/insert")
    public ResultUtil insert(HttpServletRequest request, @RequestBody CollectEntity collectEntity) {
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
        Integer i = collectService.insert(collectEntity,userId);
        if (i == 0) {
            return ResultUtil.respinseFail("收藏失败");
        }else if (i == 2){
            return ResultUtil.respinseFail("你已收藏");
        }
        return ResultUtil.respinseSuccess("收藏成功");
    }

    @PostMapping("/del")
    public ResultUtil del(HttpServletRequest request, @RequestBody CollectEntity collectEntity) {
        //判断是否登录
        String userToken = request.getHeader("userToken");
        if (userToken == null || userToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(userToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = collectService.del(collectEntity.getCollectId());
        if (i == 0) {
            return ResultUtil.respinseFail("删除失败");
        }
        return ResultUtil.respinseSuccess("删除成功");
    }
}
