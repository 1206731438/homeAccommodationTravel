package com.example.homeaccommodationtravel.controller;

import com.example.homeaccommodationtravel.entity.HomeTypeEntity;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.HomeTypeService;
import com.example.homeaccommodationtravel.utils.JWTUtil;
import com.example.homeaccommodationtravel.utils.PageUtil;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.HomeTypeVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/homeType")
public class HomeTypeController {

    @Autowired
    private HomeTypeService homeTypeService;

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
        PageInfo<HomeTypeVo> pageInfo = homeTypeService.selectPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    /**
     * 添加类型
     * @param request
     * @param homeTypeEntity
     * @return
     */
    @PostMapping("/insert")
    public ResultUtil insert(HttpServletRequest request, @RequestBody HomeTypeEntity homeTypeEntity) {
        //判断是否登录
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(adminToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer adminId = JWTUtil.verifyAdminToken(adminToken);
        Integer i = homeTypeService.insert(homeTypeEntity,adminId);
        if (i == 0) {
            return ResultUtil.respinseFail("添加失败");
        }else if (i == 2) {
            return ResultUtil.respinseFail("添加失败，房间类型已存在");
        }
        return ResultUtil.respinseSuccess("添加成功");
    }

    /**
     * 修改类型
     * @param request
     * @param homeTypeEntity
     * @return
     */
    @PostMapping("/update")
    public ResultUtil update(HttpServletRequest request, @RequestBody HomeTypeEntity homeTypeEntity) {
        //判断是否登录
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(adminToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer adminId = JWTUtil.verifyAdminToken(adminToken);
        Integer i = homeTypeService.update(homeTypeEntity,adminId);
        if (i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }else if (i == 2) {
            return ResultUtil.respinseFail("修改失败，类型已存在");
        }
        return ResultUtil.respinseSuccess("修改成功");
    }

    /**
     * 批量删除/删除
     * @param request
     * @param typeIds
     * @return
     */
    @PostMapping("/del")
    public ResultUtil del(HttpServletRequest request, @RequestBody List<Integer> typeIds) {
        //判断是否登录
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(adminToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = homeTypeService.del(typeIds);
        if (i == 0) {
            return ResultUtil.respinseFail("删除失败");
        }
        return ResultUtil.respinseSuccess("删除成功");
    }
}
