package com.example.homeaccommodationtravel.controller;

import com.example.homeaccommodationtravel.entity.AdminEntity;
import com.example.homeaccommodationtravel.po.UpdatePwdPo;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.AdminService;
import com.example.homeaccommodationtravel.utils.JWTUtil;
import com.example.homeaccommodationtravel.utils.PageUtil;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.AdminListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 分页查询
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
        Integer adminType = JWTUtil.getAdminType(adminToken);

        PageInfo<AdminListVo> pageInfo = adminService.selectPage(page,adminType);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public ResultUtil login(@RequestBody AdminEntity adminEntity) {
        if (adminEntity.getAdminPhone() == null || adminEntity.getAdminPhone().equals("")) {
            return ResultUtil.respinseFail("手机号不能为空");
        }else if (adminEntity.getAdminPassword() == null || adminEntity.getAdminPassword().equals("")) {
            return ResultUtil.respinseFail("密码不能为空");
        }

        return adminService.login(adminEntity);
    }

    /**
     * 添加
     * @return
     */
    @PostMapping("/insert")
    public ResultUtil insert(HttpServletRequest request, @RequestBody AdminEntity adminEntity) {
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
        return adminService.insert(adminId,adminEntity);
    }

    /**
     * 修改
     * @param request
     * @param adminEntity
     * @return
     */
    @PostMapping("/update")
    public ResultUtil update(HttpServletRequest request, @RequestBody AdminEntity adminEntity) {
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
        Integer i = adminService.update(adminEntity,adminId);
        if (i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }else if (i == 2) {
            return ResultUtil.respinseFail("修改失败，手机号已存在");
        }
        return ResultUtil.respinseSuccess("修改成功",adminEntity);
    }

    /**
     * 批量删除/删除
     * @param request
     * @param adminIdList
     * @return
     */
    @PostMapping("/del")
    public ResultUtil del(HttpServletRequest request, @RequestBody List<Integer> adminIdList) {
        //判断是否登录
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(adminToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer i = adminService.del(adminIdList);
        if(i == 0) {
            return ResultUtil.respinseFail("删除失败");
        }
        return ResultUtil.respinseSuccess("删除成功");
    }

    /**
     * 图片上传
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        // 定义存储图片的地址
        String folder = "D:\\project\\vueProject\\home_accommodation_travel_admin\\src\\imgs\\";
        // 文件夹
        File imgFolder = new File(folder);
        // 获取文件名
        String fname = file.getOriginalFilename();
        // 获取文件后缀
        String ext = "." + fname.substring(fname.lastIndexOf(".")+1);
        // 获取时间字符串
        String dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        // 生成新的文件名
        String newFileName = dateTimeFormatter + UUID.randomUUID().toString().replaceAll("-","") + ext;
        // 文件在本机的全路径
        File filePath = new File(imgFolder, newFileName);
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        try{
            file.transferTo(filePath);
            // 返回文件名
            return filePath.getName();
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 修改密码
     * @param request
     * @param updatePwdPo
     * @return
     */
    @PostMapping("/updatePwd")
    public ResultUtil updatePwd(HttpServletRequest request, @RequestBody UpdatePwdPo updatePwdPo) {
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
        Integer i = adminService.updatePwd(updatePwdPo,adminId);
        if (i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }else if (i == 2) {
            return ResultUtil.respinseFail("修改失败，原密码错误");
        }
        return ResultUtil.respinseSuccess("修改成功");
    }
}
