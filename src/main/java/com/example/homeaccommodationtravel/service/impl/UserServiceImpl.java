package com.example.homeaccommodationtravel.service.impl;

import com.example.homeaccommodationtravel.entity.AdminEntity;
import com.example.homeaccommodationtravel.entity.UserEntity;
import com.example.homeaccommodationtravel.mapper.UserMapper;
import com.example.homeaccommodationtravel.po.UpdatePwdPo;
import com.example.homeaccommodationtravel.service.UserService;
import com.example.homeaccommodationtravel.utils.JWTUtil;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultUtil login(UserEntity userEntity) {
        //根据用户名查询
        List<UserEntity> users = userMapper.getUserByPhone(userEntity.getUserPhone());
        //不存在提示
        if (users == null || users.size() == 0) {
            return ResultUtil.respinseFail("用户不存在！");
        }
        //如果存在，判断密码是否正确
        UserEntity user = users.get(0);
        //判断加密后的密码是否和查出用户密码一致
        if (DigestUtils.md5DigestAsHex((userEntity.getUserPassword()+user.getUserSalt()).getBytes(StandardCharsets.UTF_8)).equals(user.getUserPassword())) {
            //如果一致，生成token，返回数据
            String token = JWTUtil.createToken(user.getUserId());
            UserVo userVo = new UserVo();
            userVo.setUserId(user.getUserId());
            userVo.setUserToken(token);
            userVo.setUserImg(user.getUserImg());
            userVo.setUserAge(user.getUserAge());
            userVo.setUserNickname(user.getUserNickname());
            userVo.setUserSex(user.getUserSex());
            userVo.setUserPhone(user.getUserPhone());
            return ResultUtil.respinseSuccess("登陆成功",userVo);
        }
        return ResultUtil.respinseFail("密码错误");
    }

    @Override
    public ResultUtil register(UserEntity userEntity) {
        //根据手机号判断用户是否存在
        List<UserEntity> userEntities = userMapper.getUserByPhone(userEntity.getUserPhone());
        if (userEntities != null && userEntities.size() != 0) {
            return ResultUtil.respinseFail("注册失败，手机号已存在");
        }
        //生成密钥
        String salt = UUID.randomUUID().toString();
        userEntity.setUserSalt(salt);
        //加密
        String userPassword = DigestUtils.md5DigestAsHex((userEntity.getUserPassword() + salt).getBytes(StandardCharsets.UTF_8));
        userEntity.setUserPassword(userPassword);
        //添加
        Integer i = userMapper.register(userEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("注册失败");
        }
        return ResultUtil.respinseSuccess("注册成功");
    }

    @Override
    public Integer update(UserEntity userEntity) {
        //根据手机号判断用户是否存在
        Integer i = null;
        List<UserEntity> userEntities = userMapper.getUserByPhone(userEntity.getUserPhone());
        if (userEntities != null && userEntities.size() > 0) {
            UserEntity user = userEntities.get(0);
            if (user.getUserId() == userEntity.getUserId()) {
                //代表手机号未修改
                i = userMapper.update(userEntity);
            }else {
                //代表手机号已被其他人使用
                i = 2;
            }
        }else {
            i = userMapper.update(userEntity);
        }
        //修改
        return i;
    }

    @Override
    public Integer updateUserPwd(UpdatePwdPo updatePwdPo, Integer userId) {
        Integer i = 0;
        //先根据id查询原密码
        List<UserEntity> userEntities = userMapper.selUserById(userId);
        if (userEntities != null && userEntities.size() != 0) {
            UserEntity user = userEntities.get(0);
            //判断密码加密后是否一致
            if (DigestUtils.md5DigestAsHex((updatePwdPo.getOldPassword()+user.getUserSalt()).getBytes(StandardCharsets.UTF_8)).equals(user.getUserPassword())) {
                //可以修改密码
                //生成密钥
                String salt = UUID.randomUUID().toString();
                //为员工生成新密码
                String userPassword = DigestUtils.md5DigestAsHex((updatePwdPo.getNewPassword() + salt).getBytes(StandardCharsets.UTF_8));
                //修改
                i = userMapper.updateUserPwd(userId,salt,userPassword);
            }else {
                i = 2;
            }
        }
        return i;
    }
}
