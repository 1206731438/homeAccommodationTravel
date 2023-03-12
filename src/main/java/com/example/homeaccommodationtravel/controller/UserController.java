package com.example.homeaccommodationtravel.controller;

import com.example.homeaccommodationtravel.entity.AdminEntity;
import com.example.homeaccommodationtravel.entity.UserEntity;
import com.example.homeaccommodationtravel.po.UpdatePwdPo;
import com.example.homeaccommodationtravel.service.UserService;
import com.example.homeaccommodationtravel.utils.JWTUtil;
import com.example.homeaccommodationtravel.utils.ResultUtil;
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
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public ResultUtil login(@RequestBody UserEntity userEntity) {
        if (userEntity.getUserPhone() == null || userEntity.getUserPhone().equals("")) {
            return ResultUtil.respinseFail("手机号不能为空");
        }else if (userEntity.getUserPassword() == null || userEntity.getUserPassword().equals("")) {
            return ResultUtil.respinseFail("密码不能为空");
        }
        return userService.login(userEntity);
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public ResultUtil register(@RequestBody UserEntity userEntity) {
        if (userEntity.getUserPhone() == null || userEntity.getUserPhone().equals("")) {
            return ResultUtil.respinseFail("手机号不能为空");
        }else if (userEntity.getUserPassword() == null || userEntity.getUserPassword().equals("")) {
            return ResultUtil.respinseFail("密码不能为空");
        }
        return userService.register(userEntity);
    }

    /**
     * 修改信息
     * @param request
     * @param userEntity
     * @return
     */
    @PostMapping("/update")
    public ResultUtil update(HttpServletRequest request,@RequestBody UserEntity userEntity) {
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
        userEntity.setUserId(userId);
        Integer i = userService.update(userEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }else if (i == 2) {
            return ResultUtil.respinseFail("修改失败,手机号已存在");
        }
        return ResultUtil.respinseSuccess("修改成功",userEntity);
    }

    /**
     * 修改密码
     * @param request
     * @param updatePwdPo
     * @return
     */
    @PostMapping("/updatePwd")
    public ResultUtil updateUserPwd(HttpServletRequest request,@RequestBody UpdatePwdPo updatePwdPo) {
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
        Integer i = userService.updateUserPwd(updatePwdPo,userId);
        if (i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }else if(i == 2) {
            return ResultUtil.respinseFail("修改失败,原密码错误");
        }
        return ResultUtil.respinseSuccess("修改成功");
    }

    /**
     * 图片上传
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        // 定义存储图片的地址
        String folder = "D:\\project\\vueProject\\home_accommodation_travel_user\\src\\imgs\\";
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
}
