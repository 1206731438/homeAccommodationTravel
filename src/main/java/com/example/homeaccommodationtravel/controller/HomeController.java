package com.example.homeaccommodationtravel.controller;

import com.example.homeaccommodationtravel.po.HomeInsertPo;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.HomeService;
import com.example.homeaccommodationtravel.utils.JWTUtil;
import com.example.homeaccommodationtravel.utils.PageUtil;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.HomeListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

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
        PageInfo<HomeListVo> pageInfo = homeService.selectPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    /**
     * 用户分页查询
     * @param request
     * @param page
     * @return
     */
    @PostMapping("/selUserPage")
    public ResultUtil selUserPage(HttpServletRequest request, @RequestBody Page page) {
        PageInfo<HomeListVo> pageInfo = homeService.selUserPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }


    /**
     * 添加
     * @param request
     * @param homeInsertPo
     * @return
     */
    @PostMapping("/insert")
    public ResultUtil insert(HttpServletRequest request, @RequestBody HomeInsertPo homeInsertPo) {
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
        Integer i = homeService.insert(homeInsertPo,adminId);
        if (i == 0) {
            return ResultUtil.respinseFail("添加失败");
        }
        return ResultUtil.respinseSuccess("添加成功");
    }

    /**
     * 修改房间
     * @param request
     * @param homeInsertPo
     * @return
     */
    @PostMapping("/update")
    public ResultUtil update(HttpServletRequest request, @RequestBody HomeInsertPo homeInsertPo) {
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
        Integer i = homeService.update(homeInsertPo,adminId);
        if (i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }
        return ResultUtil.respinseSuccess("修改成功");
    }

    /**
     * 删除/批量删除
     * @param request
     * @param homeIds
     * @return
     */
    @PostMapping("/del")
    public ResultUtil del(HttpServletRequest request, @RequestBody List<Integer> homeIds) {
        //判断是否登录
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(adminToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = homeService.del(homeIds);
        if (i == 0) {
            return ResultUtil.respinseFail("删除失败");
        }
        return ResultUtil.respinseSuccess("删除成功");
    }
}
